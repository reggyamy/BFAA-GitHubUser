package com.dicodingsubmission.githupuser.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "database_user")
data class UserEntity(
    @PrimaryKey var id: Int,
    var login: String?,
    var avatar_url : String?
):Serializable