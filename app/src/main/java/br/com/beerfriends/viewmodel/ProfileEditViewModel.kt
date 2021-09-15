package br.com.beerfriends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileEditViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Editar Perfil"
    }
    val text: LiveData<String> = _text
}