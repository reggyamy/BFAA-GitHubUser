package com.dicodingsubmission.githupuser.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicodingsubmission.githupuser.database.UserDao
import com.dicodingsubmission.githupuser.database.UserDatabase
import com.dicodingsubmission.githupuser.database.UserEntity

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDatabase : UserDatabase? = UserDatabase.getInstance(application)
    private var userDao : UserDao? = userDatabase?.userDao()

    fun getFavoriteUser(): LiveData<List<UserEntity>>? {
        return userDao?.getUser()
    }
}