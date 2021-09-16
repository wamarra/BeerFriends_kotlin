package br.com.beerfriends.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.beerfriends.model.Friend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFriends(friends: List<Friend>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend: Friend)

    @get:Query("SELECT * FROM Friend")
    val friends: LiveData<List<Friend>>

    @Query("DELETE FROM Friend")
    fun deleteAll()
}