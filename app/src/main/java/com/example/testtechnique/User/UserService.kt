package com.example.testtechnique.User

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserService {
    public fun findUsers(onResult: (ApiResponse?) -> Unit, onError: (Throwable) -> Unit)  {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .build()
        val userApi : UserApi = retrofit.create(UserApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = userApi.getUsers().execute().body()
                withContext(Dispatchers.Main) {
                    onResult(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }

    }

}