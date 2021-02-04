package com.example.kenyaeducationfund.ui.Main.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.databinding.FragmentCreatePostBinding
import com.example.kenyaeducationfund.other.EventObserver
import com.example.kenyaeducationfund.ui.Main.viewmodels.CreatePostViewModel
import com.example.kenyaeducationfund.ui.slideUpViews
import com.example.kenyaeducationfund.ui.snackbar
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreatePostFragment : Fragment(R.layout.fragment_create_post) {
    private lateinit var binding: FragmentCreatePostBinding
    @Inject
    lateinit var glide: RequestManager

    private val viewModel: CreatePostViewModel by viewModels()

    private lateinit var cropContent: ActivityResultLauncher<Any?>

    private val cropActivityResultContract = object : ActivityResultContract<Any?, Uri?>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .setAspectRatio(16, 9)
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(requireContext())
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uri
        }
    }

    private var curImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cropContent = registerForActivityResult(cropActivityResultContract) {
            it?.let {
                viewModel.setCurImageUri(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePostBinding.bind(view)
        subscribeToObservers()
        binding.btnSetPostImage.setOnClickListener {
            cropContent.launch(null)
        }
        binding.ivPostImage.setOnClickListener {
            cropContent.launch(null)
        }
        binding.btnPost.setOnClickListener {
            curImageUri?.let { uri ->
                viewModel.createPost(uri, binding.etPostDescription.text.toString())
            } ?: snackbar(getString(R.string.error_no_image_chosen))
        }
        slideUpViews(requireContext(),binding.ivPostImage,binding.btnSetPostImage,binding.tilPostText,binding.btnPost)
    }

    private fun subscribeToObservers() {
        viewModel.curImageUri.observe(viewLifecycleOwner) {
            curImageUri = it
            binding.btnSetPostImage.isVisible = false
            glide.load(curImageUri).into(binding.ivPostImage)
        }
        viewModel.createPostStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.createPostProgressBar.isVisible = false
                snackbar(it)
            },
            onLoading = { binding.createPostProgressBar.isVisible = true }
        ) {
            binding.createPostProgressBar.isVisible = false
            findNavController().popBackStack()
        })
    }
}