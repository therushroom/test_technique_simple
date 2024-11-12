package com.example.testtechnique.UserRecylerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtechnique.R
import com.example.testtechnique.User.User


class UserViewHolder(private val userView : View) : RecyclerView.ViewHolder(userView) {

    private val avatarView : ImageView = userView.findViewById(R.id.Avatar)
    private val fullNameView : TextView = userView.findViewById(R.id.fullname)
    private val emailView : TextView = userView.findViewById(R.id.email)

    fun bind(user : User) {
        Glide.with(this.userView).load(user.avatar).into(avatarView)
        fullNameView.text = buildString {
            append(user.firstName)
            append(" ")
            append(user.lastName)
        }
        emailView.text = user.email

    }

}
