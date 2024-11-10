package com.example.testtechnique.User

import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class UserRepository @Inject constructor() {
    public suspend fun getUsers() : List<User>  {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
        val userApi : UserApi = retrofit.create(UserApi::class.java)

        val response = userApi.getUsers().execute().body()
        delay(1000)
        return response?.data ?: emptyList()

    }

}