package com.example.testtechnique.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(userRepository : UserRepository): ViewModel(){

    private val _users : MutableStateFlow<List<User>> = MutableStateFlow(

        listOf(
            User(firstName = "walid", lastName = "antaki", avatar = "null", email = "none", id = 1)
        )

    )
    val users : StateFlow<List<User>> = _users;

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _users.value = userRepository.getUsers()
            }
        }
    }


}