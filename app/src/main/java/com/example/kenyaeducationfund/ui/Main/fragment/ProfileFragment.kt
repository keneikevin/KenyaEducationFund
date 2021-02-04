package com.example.kenyaeducationfund.ui.Main.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.other.EventObserver
import com.example.kenyaeducationfund.ui.Main.viewmodels.BasePostViewModel
import com.example.kenyaeducationfund.ui.Main.viewmodels.ProfileViewModel
import com.example.kenyaeducationfund.ui.snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
open class  ProfileFragment :BasePostFragment(R.layout.fragment_profile){
    override val postProgressBar: ProgressBar
        get() = profilePostsProgressBar
    override val basePostViewModel: BasePostViewModel
        get() {
            val vm:ProfileViewModel by viewModels()
            return vm
        }

    protected val viewModel:ProfileViewModel
    get() = basePostViewModel as ProfileViewModel

    protected open val uid:String
    get() = FirebaseAuth.getInstance().uid!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
        subscribeToObservers()

        btnToggleFollow.isVisible = false
        viewModel.loadProfile(uid)

    }

    private fun setUpRecycleView() = rvPosts.apply{
        adapter = postAdapter
        itemAnimator=null
        layoutManager = LinearLayoutManager(requireContext())
    }
    private fun subscribeToObservers(){
            viewModel.profileMeta.observe(viewLifecycleOwner,EventObserver(
                    onError = {
                              profileMetaProgressBar.isVisible =false
                        snackbar(it)
                    },
                    onLoading = {profileMetaProgressBar.isVisible =true}
            ){user->
                profileMetaProgressBar.isVisible = false
                tvUsername.text= user.username
                tvProfileDescription.text = if(user.description.isEmpty()){
                    requireContext().getString(R.string.no_description)
                } else user.description
            glide.load(user.profilePictureUrl).into(ivProfileImage)

            })
    }
}




