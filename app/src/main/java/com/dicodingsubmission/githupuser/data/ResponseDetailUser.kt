package com.dicodingsubmission.githupuser.data

data class ResponseDetailUser(
    val avatar_url : String,
    val login : String,
    val name : String,
    val company : String,
    val location : String,
    val followers : Int,
    val following : Int,
    val email : String,
    val followers_url : String,
    val following_url: String,
    val public_repos : Int,
    val repos_url: String
)