package com.example.bookstore.ui.login.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginResult = MutableLiveData<Login>()
    val loginResult: LiveData<Login> = _loginResult

    fun login(username: String, password: String) {
//        viewModelScope.launch(Dispatchers.Main) {
//            _loginResult.value = UserService().authentication(username, password)
//        }
    }
}