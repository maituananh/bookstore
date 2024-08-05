package com.example.bookstore.service

import com.example.bookstore.model.Login

class UserService {
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