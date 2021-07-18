package com.dicodingsubmission.githupuser.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicodingsubmission.githupuser.api.ApiConfig
import com.dicodingsubmission.githupuser.data.DataUser
import com.dicodingsubmission.githupuser.data.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {

    val user = MutableLiveData<ArrayList<DataUser>>()

    fun setSearchUser(query: String){

        ApiConfig.client.getSearchUser(query).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful){
                    user.postValue(response.body()?.items as ArrayList<DataUser>)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                t.message?.let { Log.d("Error", it)}
            }
        })

    }
    fun getLiveData(): LiveData<ArrayList<DataUser>> = user
}

