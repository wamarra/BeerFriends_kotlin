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
    @SerializedName("name") val name: String = "",

    @ColumnInfo(name = "email")
    @SerializedName("email") val email: String = "",

    @ColumnInfo(name = "phone")
    @SerializedName("phone") val phone: String = "",

    @ColumnInfo(name = "website")
    @SerializedName("website") val website: String = ""
)