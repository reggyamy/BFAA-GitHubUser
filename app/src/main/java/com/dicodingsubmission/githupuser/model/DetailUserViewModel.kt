package com.dicodingsubmission.githupuser.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicodingsubmission.githupuser.api.ApiConfig
import com.dicodingsubmission.githupuser.data.ResponseDetailUser
import com.dicodingsubmission.githupuser.database.UserDao
import com.dicodingsubmission.githupuser.database.UserDatabase
import com.dicodingsubmission.githupuser.database.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {
    val login = MutableLiveData<ResponseDetailUser>()
    fun getDetailUser():LiveData<ResponseDetailUser> = login

    private var userDatabase : UserDatabase? = UserDatabase.getInstance(application)
    private var userDao : UserDao? = userDatabase?.userDao()
    fun setDetailUser(username: String?) {
        if (username != null) {
            ApiConfig.client.getDetailUser(username).enqueue(object : Callback<ResponseDetailUser>{
                override fun onResponse(
                    call: Call<ResponseDetailUser>,
                    response: Response<ResponseDetailUser>
                ) {
                    if (response.isSuccessful){
                        login.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                    Log.d("onFailure", t.message!!)
                }
            })
        }
    }


    fun addFavoriteUser(id:Int, login: String?, avatar_url : String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserEntity(id, login, avatar_url)
            userDao?.addUser(user)}
    }

    suspend fun checkFavoriteUser(id : Int)= userDao?.checkUser(id)

    fun deleteFavoriteUser(id: Int) = CoroutineScope(Dispatchers.IO).launch { userDao?.deleteUser(id) }


}
