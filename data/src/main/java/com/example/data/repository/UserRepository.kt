package com.example.data.repository

import com.example.data.network.setting.ItBookApi
import com.example.domain.i_repository.IUserRepository
import com.example.domain.model.user.User
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepository : IUserRepository {
    @Inject
    @ItBookApi
    lateinit var provideRetrofit: Retrofit

    override suspend fun authentication(username: String, password: String): User {
        return User("1", "admin", "token")
    }

}