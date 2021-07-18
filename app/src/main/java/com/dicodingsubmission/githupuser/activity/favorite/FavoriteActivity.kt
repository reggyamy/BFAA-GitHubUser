package com.dicodingsubmission.githupuser.activity.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.githupuser.activity.detailUser.DetailUserActivity
import com.dicodingsubmission.githupuser.adapter.GitHubUserAdapter
import com.dicodingsubmission.githupuser.data.DataUser
import com.dicodingsubmission.githupuser.database.UserEntity
import com.dicodingsubmission.githupuser.databinding.ActivityFavoriteBinding
import com.dicodingsubmission.githupuser.model.FavoriteViewModel
import java.util.ArrayList

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: GitHubUserAdapter
    private lateinit var  viewModel : FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GitHubUserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.apply {
            rvGithubUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvGithubUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it!=null){
                val users = converter(it)
                adapter.setData(users)
            }
        })

        adapter.setOnItemClickCallback(object : GitHubUserAdapter.OnItemClickCallback{
            override fun onItemClicked(dataUser: DataUser) {
                val moveDetail = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                moveDetail.putExtra(DetailUserActivity.KEY_DETAIL,dataUser.login)
                moveDetail.putExtra(DetailUserActivity.KEY_ID,dataUser.id)
                moveDetail.putExtra(DetailUserActivity.KEY_PHOTO,dataUser.avatar_url)
                startActivity(moveDetail)
            }

        })

    }

    private fun converter(list: List<UserEntity>): ArrayList<DataUser> {
        val allUser = ArrayList<DataUser>()
        for (user in list){
            val users = DataUser(user.login,user.avatar_url,user.id)
            allUser.add(users)
        }
        return allUser
    }
}


