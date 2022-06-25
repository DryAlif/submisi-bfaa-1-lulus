package com.equinox.myfirstandroidsubmission.detail

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.equinox.myfirstandroidsubmission.R
import com.equinox.myfirstandroidsubmission.databinding.ActivityUserDetailBinding
import com.equinox.myfirstandroidsubmission.model.User

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DarkTheme)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val user = intent.getParcelableExtra<User>("DATA") as User
        bindDetailView(user)

        //Create Log for test
        val logText = "Username : ${user.username.toString()},\n" +
                "Avatar : ${user?.avatar.toString()},\n" +
                "name : ${user?.name.toString()},\n" +
                "Company : ${user?.company.toString()},\n" +
                "Repository : ${user?.repository.toString()},\n" +
                "Location : ${user?.location.toString()}"
        Log.d("Detail Data", logText)
    }

    private fun bindDetailView(user: User) {
        with(binding) {
            userAvatar.avatarLoader(user.avatar)
            textViewName.text = user.name
            textViewUsername.text = user.username
            textViewFollowers.text = " ${user?.followers.toString()} followers"
            textViewFollowing.text = " ${user?.following.toString()} following"
            textViewCompany.text = " Work at ${user?.company.toString()}"
            textViewLocation.text = user.location
            textViewRepository.text =" ${user?.repository.toString()} Repository"
        }
    }


    private fun ImageView.avatarLoader(url: Int?) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .into(this)
    }
}