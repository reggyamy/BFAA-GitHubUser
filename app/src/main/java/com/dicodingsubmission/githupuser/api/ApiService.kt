package com.dicodingsubmission.githupuser.api

import com.dicodingsubmission.githupuser.data.DataUser
import com.dicodingsubmission.githupuser.data.ResponseDetailUser
import com.dicodingsubmission.githupuser.data.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(@Query("q") query: String): Call<ResponseUser>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(@Path("username") username:String):Call<ResponseDetailUser>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") following :String):Call<ArrayList<DataUser>>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") followers :String):Call<ArrayList<DataUser>>

}
