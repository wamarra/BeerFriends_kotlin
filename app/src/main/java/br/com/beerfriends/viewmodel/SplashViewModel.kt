package br.com.beerfriends.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.model.SplashRepository
import br.com.beerfriends.model.User

class SplashViewModel(private val splashRepository: SplashRepository) : ViewModel() {
    val authenticatedUserLiveData = MutableLiveData<User?>()

    fun checkIfUserIsAuthenticated() {
        splashRepository.checkIfUserIsAuthenticatedInFirebase(authenticatedUserLiveData)
    }

    class SplashViewModelFactory(private val splashRepository: SplashRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(splashRepository) as T
        }

    }
}
