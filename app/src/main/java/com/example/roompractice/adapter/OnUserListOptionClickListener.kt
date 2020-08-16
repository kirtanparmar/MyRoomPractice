package com.example.roompractice.adapter

import com.example.roompractice.roomDatabase.tables.UserTable

interface OnUserListOptionClickListener {
    fun showUserDetails(model: UserTable)
}