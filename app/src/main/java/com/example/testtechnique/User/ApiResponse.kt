package com.example.testtechnique.User

data class Support(val url : String, val text : String);

data class ApiResponse(val page : Int,
                       val perPage : Int,
                       val total : Int,
                       val totalPages: Int,
                       val data : List<User>,
                       val support : Support
    )
