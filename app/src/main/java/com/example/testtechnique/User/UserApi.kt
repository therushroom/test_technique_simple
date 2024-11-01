package com.example.testtechnique.User

import retrofit2.Call
import retrofit2.http.GET

// https://reqres.in/api/users?page=1

interface UserApi {
    @GET("api/users")
    fun getUsers(): Call<ApiResponse>
}