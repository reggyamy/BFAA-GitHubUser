package com.dicodingsubmission.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var users = MutableLiveData<ArrayList<DataUser>>()

    fun setFavoriteUser(context: Context){
        val cursor = context.contentResolver.query(ObjectDatabase.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val converter = ConverterHelper.convertCursorToArrayList(cursor)
        users.postValue(converter)
    }

    fun getFavoriteUser(): LiveData<ArrayList<DataUser>> {
        return users
    }
}