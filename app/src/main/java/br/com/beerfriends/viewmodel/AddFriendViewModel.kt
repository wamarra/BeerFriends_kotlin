package br.com.beerfriends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddFriendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Adicionar Amigo"
    }
    val text: LiveData<String> = _text
}