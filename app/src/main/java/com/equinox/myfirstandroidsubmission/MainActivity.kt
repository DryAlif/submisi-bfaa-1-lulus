package com.equinox.myfirstandroidsubmission

import android.content.Intent
import android.content.res.Configuration
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.equinox.myfirstandroidsubmission.adapter.ListUsersAdapter
import com.equinox.myfirstandroidsubmission.detail.UserDetailActivity
import com.equinox.myfirstandroidsubmission.model.User
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var rv_Users : RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DarkTheme)
        setContentView(R.layout.activity_main)

        rv_Users = findViewById(R.id.rv_github_users)
        rv_Users.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private fun showRecyclerList() {


        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rv_Users.layoutManager = GridLayoutManager(this, 2)
        } else {
            rv_Users.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUsersAdapter(list)
        rv_Users.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intentToDetail = Intent(this@MainActivity, UserDetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataUserName = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val listUser = ArrayList<User>()
            for (position in dataName.indices) {
                val user = User(
                    dataUserName[position],
                    dataName[position],
                    dataLocation[position],
                    dataRepository[position],
                    dataCompany[position],
                    dataFollowers[position],
                    dataFollowing[position],
                    dataAvatar.getResourceId(position, -1),

                )
                listUser.add(user)
            }
            return listUser
        }
}