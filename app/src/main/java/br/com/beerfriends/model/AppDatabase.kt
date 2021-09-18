package br.com.beerfriends.model

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.beerfriends.model.dao.FriendDao
import br.com.beerfriends.model.dao.UserDao

@Database(entities = [Friend::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
    abstract fun userDao(): UserDao
}