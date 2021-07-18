package com.dicodingsubmission.consumerapp

import android.database.Cursor

object ConverterHelper {
    fun convertCursorToArrayList(cursor: Cursor?): ArrayList<DataUser>{
        val users = ArrayList<DataUser>()

        if (cursor!=null){
            while (cursor.moveToNext()){
                val login = cursor.getString(cursor.getColumnIndexOrThrow(ObjectDatabase.FavoriteUserColumns.LOGIN))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(ObjectDatabase.FavoriteUserColumns.AVATAR_URL))
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ObjectDatabase.FavoriteUserColumns.ID))

                users.add(
                    DataUser(login,avatarUrl,id)
                )
            }
        }
        return users
    }
}