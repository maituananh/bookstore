package com.example.bookstore.ui.login.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.Login
import com.example.bookstore.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Login>()
    val loginResult: LiveData<Login> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _loginResult.value = UserService().authentication(username, password)
        }
    }
}