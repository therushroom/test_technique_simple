package com.example.testtechnique

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtechnique.User.User
import com.example.testtechnique.User.UserService
import com.example.testtechnique.UserRecylerView.UserAdapter

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var users = mutableListOf<User>();
        UserService().findUsers(
            onResult = { data ->
                //
                for( user in data?.data!!) {
                    users.add(user)
                    Log.i("user " , user.avatar)
                }
                adapter = UserAdapter(users)
                recyclerView.adapter = adapter
            },
            onError = {error -> Log.i("error", error.message?:"wd")}

        )


    }

}