package br.com.beerfriends

import android.app.Application
import androidx.room.Room
import br.com.beerfriends.model.AppDatabase

class BeerFriendsApplication: Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "friend_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
