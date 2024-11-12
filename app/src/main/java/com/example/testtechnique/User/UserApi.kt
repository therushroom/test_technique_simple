package com.example.testtechnique.User

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://reqres.in/api/users?page=1

interface UserApi {
    @GET("api/users?")
    fun getUsers(@Query("page") page: Int): Call<ApiResponse>
}