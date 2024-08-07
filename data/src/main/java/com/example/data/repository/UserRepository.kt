package com.example.data.repository

import com.example.domain.model.Login

class UserRepository {
    suspend fun authentication(username: String, password: String): Login {
        try {
            if (username == "admin") {
                return Login(1, "admin", "token")
            }

            return Login(0, "", "")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}