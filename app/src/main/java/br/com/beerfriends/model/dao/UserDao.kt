package br.com.beerfriends.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.beerfriends.model.User

@Dao
interface UserDao {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("SELECT * FROM User WHERE uid = :uid")
    fun getUserByUid(uid: String): LiveData<User>
}