package com.example.domain.i_repository

import com.example.domain.model.user.User

interface IUserRepository {
    suspend fun authentication(username: String, password: String): User
}