package com.example.kenyaeducationfund.ui.Main.fragment

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.ui.Main.viewmodels.BasePostViewModel
import com.example.kenyaeducationfund.ui.Main.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment :BasePostFragment(R.layout.fragment_home){
    override val postProgressBar: ProgressBar
        get() =allPostsProgressBar

    override val basePostViewModel: BasePostViewModel
        get() {
            val vm: HomeViewModel by viewModels()
            return vm
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecylerView()
    }
    private fun setUpRecylerView() = rvAllPosts.apply{
        adapter=postAdapter
        layoutManager = LinearLayoutManager(requireContext())
        itemAnimator = null
    }
}