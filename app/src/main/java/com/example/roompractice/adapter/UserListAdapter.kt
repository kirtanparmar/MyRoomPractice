package com.example.roompractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roompractice.databinding.UserListViewBinding
import com.example.roompractice.roomDatabase.tables.UserTable

class UserListAdapter(
    diffUtil: DiffUtil.ItemCallback<UserTable>,
    val listener: OnUserListOptionClickListener
) :
    ListAdapter<UserTable, UserListAdapter.UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            UserListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class UserViewHolder(private val view: UserListViewBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun setData(model: UserTable, listener: OnUserListOptionClickListener) {
            view.model = model
            view.root.setOnClickListener { listener.showUserDetails(model) }
        }
    }

}