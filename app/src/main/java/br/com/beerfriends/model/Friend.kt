package br.com.beerfriends.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Friend")
data class Friend(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String = "",

    @ColumnInfo(name = "email")
    @SerializedName("email") var email: String = "",

    @ColumnInfo(name = "phone")
    @SerializedName("phone") var phone: String = "",

    @ColumnInfo(name = "website")
    @SerializedName("website") var website: String = ""
)