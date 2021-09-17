package br.com.beerfriends.viewmodel

import androidx.lifecycle.*
import br.com.beerfriends.BeerFriendsApplication
import br.com.beerfriends.model.EventWrapper
import br.com.beerfriends.model.FriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendListViewModel(private val repository: FriendRepository) : ViewModel() {
    val items = repository.getFriends()
    val requestError = MutableLiveData<EventWrapper<String>>()

    fun refreshFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchFriends()

                if (response.isSuccessful) {
                    BeerFriendsApplication.database!!.friendDao().insertAllFriends(response.body()!!)
                } else {
                    withContext(Dispatchers.Main) {
                        requestError.value = EventWrapper("Erro ao atualizar os dados")
                    }
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    requestError.value = EventWrapper("Erro ao atualizar os dados")
                }
            }
        }
    }

    class FriendListViewModelFactory(private val repository: FriendRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FriendListViewModel(repository) as T
        }
    }
}