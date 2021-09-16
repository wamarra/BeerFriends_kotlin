package br.com.beerfriends.model

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.beerfriends.model.dao.FriendDao

@Database(entities = [Friend::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}