package br.com.beerfriends.network

import br.com.beerfriends.model.Friend
import retrofit2.Response
import retrofit2.http.GET

interface FriendAPI {

    @GET("users")
    suspend fun fetchAllFriends(): Response<List<Friend>>
}