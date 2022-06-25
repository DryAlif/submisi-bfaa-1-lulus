package com.equinox.myfirstandroidsubmission.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.equinox.myfirstandroidsubmission.R
import com.equinox.myfirstandroidsubmission.model.User
import kotlin.math.log


class ListUsersAdapter
    (
        private val listUser: ArrayList<User>
    ) : RecyclerView.Adapter<ListUsersAdapter.ListViewUserHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userAvatar: ImageView = itemView.findViewById(R.id.img_user_photo)
        var tvUserName: TextView = itemView.findViewById(R.id.tv_user_name)
        var tvUserCompany: TextView = itemView.findViewById(R.id.tv_user_company)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewUserHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.user_row_github, parent, false)

        return ListViewUserHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewUserHolder, position: Int) {
        var user = listUser[position]
        Log.d("ListUser Photo", "${user.avatar}")
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .circleCrop()
            .into(holder.userAvatar)
        holder.tvUserName.text = user.name
        holder.tvUserCompany.text = """
            Company: ${user.company}
            Location: ${user.location}
            Followers: ${user.followers}
        """.trimIndent()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}