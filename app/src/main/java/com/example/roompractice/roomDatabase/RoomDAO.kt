package com.example.roompractice.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roompractice.roomDatabase.tables.UserTable

@Dao
interface RoomDAO {

    @Query("Select * from user_table")
    fun getUsers(): LiveData<List<UserTable>?>

    @Insert
    suspend fun insertUser(model: UserTable)

    @Query("Select * from user_table where _id = :id")
    suspend fun getUser(id: Int): UserTable?

}