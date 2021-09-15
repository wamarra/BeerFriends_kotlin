package br.com.beerfriends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FriendListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lista de amigos"
    }
    val text: LiveData<String> = _text
}