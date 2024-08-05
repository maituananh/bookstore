package com.example.bookstore.ui.login.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    fun login(username: String, password: String) {
        if (username == "admin") {
            _loginResult.value = LoginResponse(1, "admin", "token")
        } else {
            _loginResult.value = LoginResponse(0, "", "")
        }
    }
}