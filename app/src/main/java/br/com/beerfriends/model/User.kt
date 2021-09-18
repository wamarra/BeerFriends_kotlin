package br.com.beerfriends.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "User")
data class User constructor(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uid")
    @SerializedName("uid") var uid: String,

    @ColumnInfo(name = "name")
    @SerializedName("name") var name: String = "",

    @ColumnInfo(name = "email")
    @SerializedName("email") var email: String = "",

    @Exclude var isNew: Boolean = false

) : Serializable {
    companion object {
        val REF_NAME = "users"
    }
}