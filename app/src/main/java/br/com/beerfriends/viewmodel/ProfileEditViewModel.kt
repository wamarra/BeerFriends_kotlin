package br.com.beerfriends.viewmodel

import androidx.lifecycle.*
import br.com.beerfriends.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileEditViewModel(private val repository: UserRepository, private val uid: String?)  : ViewModel() {
    private val requestError = MutableLiveData<EventWrapper<String>>()
    val user =  repository.getUserByUid(uid!!)

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.saveUser(user)
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    requestError.value = EventWrapper("Erro ao salvar o usuário")
                }
            }
        }
    }

    class ProfileEditViewModelFactory(private val repository: UserRepository, private val uid: String?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileEditViewModel(repository, uid) as T
        }
    }
}