package com.example.roompractice.activity.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roompractice.repository.UserRepository
import com.example.roompractice.roomDatabase.tables.UserTable

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository = UserRepository(application)
    private val users = userRepository.getUsers()

    fun insert(model: UserTable) = userRepository.insert(model)

    fun getUsers() = users

}