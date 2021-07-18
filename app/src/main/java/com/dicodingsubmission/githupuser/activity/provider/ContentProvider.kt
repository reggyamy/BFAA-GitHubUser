package com.dicodingsubmission.githupuser.activity.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicodingsubmission.githupuser.database.UserDao
import com.dicodingsubmission.githupuser.database.UserDatabase

class ContentProvider : ContentProvider() {

    companion object{
        private const val PACKAGE_SENDER = "com.dicodingsubmission.githupuser"
        private const val TABLE_NAME = "database_user"
        private const val ID_DATA_USER_FAVORITE = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            uriMatcher.addURI(PACKAGE_SENDER, TABLE_NAME, ID_DATA_USER_FAVORITE)
        }
    }

    private lateinit var userDao: UserDao

    override fun onCreate(): Boolean {
        userDao = context?.let { context ->
            UserDatabase.getInstance(context)?.userDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?
        when (uriMatcher.match(uri)) {
           ID_DATA_USER_FAVORITE -> {
                cursor = userDao.findUser()
                if (context!=null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> cursor = null
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }



    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}