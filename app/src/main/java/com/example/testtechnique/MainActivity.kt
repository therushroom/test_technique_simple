package com.example.testtechnique

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtechnique.User.User
import com.example.testtechnique.User.UserService
import com.example.testtechnique.UserRecylerView.UserAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private var pageButtons = mutableListOf<Button>();
    private lateinit var layoutButtons: LinearLayout // where to put the page buttons
    private lateinit var layoutLoader : LinearLayout // where we are gonna put the loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        layoutButtons = findViewById(R.id.linearlayout2)
        layoutLoader = findViewById(R.id.linearlayout3)


        val progressBar = ProgressBar(this)
        val params = LinearLayout.LayoutParams(400, 400)
        progressBar.layoutParams = params

        layoutLoader.addView(progressBar)

        removeLoader()


        val numPages = 2 // if we have database we can fetch it
        addPageButtons(numPages)



        // initilise recycler with page = 1
        updateRecycler(1)




    }


    private fun updateRecycler(page : Int) {
        var users = mutableListOf<User>()

        showLoader()
        // remove current page items
        adapter = UserAdapter(emptyList())
        recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            var data = UserService().findUsers(page)
            for( user in data?.data!!) {
                users.add(user)
                Log.i("user " , user.avatar)
            }
            delay(400) // add small delay to be sure
            withContext(Dispatchers.Main) {
                adapter = UserAdapter(users) // return
                recyclerView.adapter = adapter
                removeLoader()
            }
        }



    }





    private fun addPageButtons(numPages : Int) {
        for(i in 1..numPages) {
            val pageButton = Button(this).apply {
                text = "$i"
                textSize = 20f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            pageButton.setOnClickListener {
                // TODO : dont reload current page = i
                updateRecycler(i)
            }
            pageButtons.add(pageButton)
            layoutButtons.addView(pageButton)
        }
    }


    private fun showLoader() {
        layoutLoader.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1400)
    }

    private fun removeLoader() {
        layoutLoader.layoutParams = LinearLayout.LayoutParams(0,0)
    }

}

