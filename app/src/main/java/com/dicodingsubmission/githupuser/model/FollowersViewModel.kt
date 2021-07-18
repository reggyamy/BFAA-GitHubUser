package com.dicodingsubmission.githupuser.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicodingsubmission.githupuser.api.ApiConfig
import com.dicodingsubmission.githupuser.data.DataUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {
    val followers = MutableLiveData<ArrayList<DataUser>>()
    fun getFollowers() : LiveData<ArrayList<DataUser>> = followers

    fun setFollowers(username : String){
       ApiConfig.client.getFollowers(username).enqueue(object : Callback<ArrayList<DataUser>>{
           override fun onResponse(call: Call<ArrayList<DataUser>>, response: Response<ArrayList<DataUser>>) {
               if (response.isSuccessful){
                   followers.postValue(response.body())
               }
           }

           override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {
               Log.d("Error", t.message.toString())
           }

       })
    }
}