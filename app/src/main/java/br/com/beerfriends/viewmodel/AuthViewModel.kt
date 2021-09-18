package br.com.beerfriends.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.model.AuthRepository
import br.com.beerfriends.model.EventWrapper
import br.com.beerfriends.model.User
import br.com.beerfriends.model.UserRepository
import com.google.firebase.auth.AuthCredential

class AuthViewModel(private val authRepository: AuthRepository,
                    private val userRepository: UserRepository) : ViewModel() {
    enum class SignInType(val type: String) {
        Google("Google"),
        Facebook("Facebook"),
        GitHub("GitHub")
    }
    val authButtonLiveData = MutableLiveData<EventWrapper<SignInType>>()
    val authenticatedUserLiveData = MutableLiveData<User>()
    val createdUserLiveData = MutableLiveData<User>()

    fun handleGoogleSignInClick() {
        authButtonLiveData.value = EventWrapper(SignInType.Google)
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        authRepository.firebaseSignInWithGoogle(googleAuthCredential, authenticatedUserLiveData)
    }

    fun createUser(user: User) {
        authRepository.createUserInFirestoreIfNotExists(user, createdUserLiveData)
        if (userRepository.getUserByUid(user.uid) == null) {
            userRepository.saveUser(user)
        }
    }

    class AuthViewModelFactory(private val authRepository: AuthRepository,
                               private val userRepository: UserRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AuthViewModel(authRepository, userRepository) as T
        }
    }
}
