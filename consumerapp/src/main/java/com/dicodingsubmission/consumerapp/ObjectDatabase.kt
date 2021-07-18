package com.dicodingsubmission.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object ObjectDatabase {
    const val AUTHORITY = "com.dicodingsubmission.githupuser"
    const val SCHEME = "content"

    internal class FavoriteUserColumns : BaseColumns{
        companion object{
            private const val TABLE_NAME = "database_user"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}
