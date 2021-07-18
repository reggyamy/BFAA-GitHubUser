package com.dicodingsubmission.githupuser.activity.detailUser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.githupuser.databinding.FramentFollowersFollowingBinding
import com.dicodingsubmission.githupuser.R
import com.dicodingsubmission.githupuser.adapter.GitHubUserAdapter
import com.dicodingsubmission.githupuser.model.FollowingViewModel

class FollowingFragment:Fragment(R.layout.frament_followers_following) {

    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: GitHubUserAdapter
    private var bind : FramentFollowersFollowingBinding? = null
    private val binding get() = bind!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val following = arguments?.getString(DetailUserActivity.KEY_DETAIL).toString()
        bind = FramentFollowersFollowingBinding.bind(view)

        adapter = GitHubUserAdapter()
        binding.apply {
            rvGithubUser.setHasFixedSize(true)
            rvGithubUser.layoutManager = LinearLayoutManager(activity)
            rvGithubUser.adapter=adapter
        }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)
        viewModel.setFollowing(following)
        viewModel.getFollowing().observe(viewLifecycleOwner,{
            if (it!=null){
                adapter.setData(it)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        bind = null
    }

}