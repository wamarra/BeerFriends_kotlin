package br.com.beerfriends.model

import androidx.lifecycle.LiveData
import br.com.beerfriends.BeerFriendsApplication

class UserRepository {

    fun saveUser(user: User) {
        BeerFriendsApplication.database!!.userDao().updateUser(user)
    }

    fun getUserByUid(uid: String): LiveData<User> {
        return BeerFriendsApplication.database!!.userDao().getUserByUid(uid)
    }
}