package com.example.bookstore.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.i_repository.IUserRepository
import com.example.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val provideUserRepository: IUserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<User>()
    val loginResult: LiveData<User> = _loginResult

    fun login(username: String, password: String) {

        viewModelScope.launch(Dispatchers.Main) {
            _loginResult.value = provideUserRepository.authentication(username, password)
        }
    }
}