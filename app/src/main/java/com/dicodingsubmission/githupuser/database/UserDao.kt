package com.dicodingsubmission.githupuser.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao{
    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM  database_user")
    fun getUser(): LiveData<List<UserEntity>>

    @Query("SELECT count(*) FROM database_user WHERE id= :id")
    suspend fun checkUser(id: Int): Int

    @Query("DELETE FROM database_user WHERE id= :id")
    suspend fun deleteUser(id: Int): Int

    @Query("SELECT * FROM database_user")
    fun findUser() : Cursor

}