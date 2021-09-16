package br.com.beerfriends.model

import androidx.lifecycle.LiveData
import br.com.beerfriends.BeerFriendsApplication
import br.com.beerfriends.network.FriendAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FriendRepository {

    private val api: FriendAPI = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FriendAPI::class.java)

    fun getFriends(): LiveData<List<Friend>> {
        return BeerFriendsApplication.database!!.friendDao().friends
    }

    suspend fun fetchFriends(): Response<List<Friend>> {
        return api.fetchAllFriends()
    }
}