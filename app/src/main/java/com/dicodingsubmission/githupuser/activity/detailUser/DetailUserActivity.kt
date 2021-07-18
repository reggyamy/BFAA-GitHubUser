package com.dicodingsubmission.githupuser.activity.detailUser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicodingsubmission.githupuser.activity.main.MainActivity
import com.dicodingsubmission.githupuser.adapter.ProfilePagerAdapter
import com.dicodingsubmission.githupuser.databinding.ActivityDetailUserBinding
import com.dicodingsubmission.githupuser.model.DetailUserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val KEY_DETAIL = "detail"
        const val KEY_ID = "id favorite user"
        const val KEY_PHOTO = "photo favorite user"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val login = intent.getStringExtra(KEY_DETAIL)

        val bundle = Bundle()
        bundle.putString(KEY_DETAIL, login)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.setDetailUser(login)
        viewModel.getDetailUser().observe(this,{
            if (it != null){
                with(binding){
                    nameUser.text=it.name
                    username.text=it.login
                    locationUser.text=it.location
                    companyUser.text=it.company
                    repository.text=it.public_repos.toString()
                    followers.text=it.followers.toString()
                    following.text=it.following.toString()
                    Glide.with(this@DetailUserActivity).load(it.avatar_url).into(photoUser)
                }
            }
        })

        val profilePagerAdapter = ProfilePagerAdapter(this, supportFragmentManager,bundle)
        with(binding){
            viewPagerDetailProfile.adapter = profilePagerAdapter
            tabMode.setupWithViewPager(viewPagerDetailProfile)
        }

        val idUser = intent.getIntExtra(KEY_ID, 0)
        val avatarURL = intent.getStringExtra(KEY_PHOTO)
        var checked = false

        CoroutineScope(Dispatchers.IO).launch {
            val id = viewModel.checkFavoriteUser(idUser)
            withContext(Dispatchers.Main){
                if (id != null) {
                    if(id>0){
                        binding.toggleFavorite.isChecked = true
                        checked = true
                    }
                    else {
                        binding.toggleFavorite.isChecked = false
                        checked = false
                    }
                }

            }
        }

        binding.toggleFavorite.setOnClickListener{
            checked = !checked
            if(checked){
                viewModel.addFavoriteUser(idUser, login, avatarURL)
            }
            else{
                viewModel.deleteFavoriteUser(idUser)
            }
            binding.toggleFavorite.isChecked = checked

        }

        binding.buttonBackDetail.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

}