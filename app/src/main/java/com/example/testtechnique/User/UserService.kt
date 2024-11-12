package com.example.testtechnique.User

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserService {
    public suspend fun findUsers(page : Int = 1) : ApiResponse?  {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
        val userApi : UserApi = retrofit.create(UserApi::class.java)

        val response = userApi.getUsers(page).execute().body()
        return response

    }

}

