package com.example.testtechnique.User

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserService {
    public suspend fun findUsers() : ApiResponse?  {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
        val userApi : UserApi = retrofit.create(UserApi::class.java)

        val response = userApi.getUsers().execute().body()
        return response

    }

}