package com.example.roompractice.repository

import android.app.Application
import com.example.roompractice.roomDatabase.MyRoomDatabase
import com.example.roompractice.roomDatabase.RoomDAO
import com.example.roompractice.roomDatabase.tables.UserTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserRepository(context: Application) {

    private var roomDAO: RoomDAO = MyRoomDatabase.getInstance(context).roomDao()
    private var userList = roomDAO.getUsers()

    fun insert(model: UserTable) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            roomDAO.insertUser(model)
//            adapter.submitList(
//                MyRoomDatabase.getInstance(this@MainActivity).roomDao().getUsers()
//            )
        }
    }

    fun getUsers() = userList

}