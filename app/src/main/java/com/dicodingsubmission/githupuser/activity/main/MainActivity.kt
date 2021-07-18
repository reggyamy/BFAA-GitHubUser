package com.dicodingsubmission.githupuser.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.githupuser.activity.detailUser.DetailUserActivity
import com.dicodingsubmission.githupuser.activity.favorite.FavoriteActivity
import com.dicodingsubmission.githupuser.activity.setting.SettingActivity
import com.dicodingsubmission.githupuser.adapter.GitHubUserAdapter
import com.dicodingsubmission.githupuser.data.DataUser
import com.dicodingsubmission.githupuser.databinding.ActivityMainBinding
import com.dicodingsubmission.githupuser.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GitHubUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GitHubUserAdapter()
        adapter.notifyDataSetChanged()


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)

        binding.apply {
            rvGithubUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithubUser.adapter = adapter

            fabFavoriteMa.setOnClickListener{
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
            }

            btnSetting.setOnClickListener {
                    startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            }

            searchGoBtn.setOnClickListener {
                searchUser()
            }


            inputUser.setOnKeyListener { _, keyCode, event ->
                if (event.action==KeyEvent.ACTION_DOWN && keyCode==KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getLiveData().observe(this,{items->
            if (items!=null){
                adapter.setData(items)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : GitHubUserAdapter.OnItemClickCallback{
            override fun onItemClicked(dataUser: DataUser) {
                val moveDetail = Intent(this@MainActivity, DetailUserActivity::class.java)
                moveDetail.putExtra(DetailUserActivity.KEY_DETAIL,dataUser.login)
                moveDetail.putExtra(DetailUserActivity.KEY_ID,dataUser.id)
                moveDetail.putExtra(DetailUserActivity.KEY_PHOTO,dataUser.avatar_url)
                startActivity(moveDetail)
            }

        })
    }

    private fun searchUser() {
        binding.apply{
            val user = inputUser.text.toString()
            if (user.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUser(user)
        }
    }

    private fun showLoading(b: Boolean) {
        if (b) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}
