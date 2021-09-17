package br.com.beerfriends.viewmodel

import androidx.lifecycle.*
import br.com.beerfriends.BeerFriendsApplication
import br.com.beerfriends.model.EventWrapper
import br.com.beerfriends.model.Friend
import br.com.beerfriends.model.FriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFriendViewModel(private val repository: FriendRepository) : ViewModel() {
    private val requestError = MutableLiveData<EventWrapper<String>>()

    fun insertFriend(friend: Friend) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                BeerFriendsApplication.database!!.friendDao().insertFriend(friend)
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    requestError.value = EventWrapper("Erro ao salvar um amigo")
                }
            }
        }
    }

    class AddFriendViewModelFactory(private val repository: FriendRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddFriendViewModel(repository) as T
        }
    }
}