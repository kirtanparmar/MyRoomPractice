package com.example.roompractice.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roompractice.roomDatabase.tables.UserTable

@Database(entities = [UserTable::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDAO

    companion object {
        @Volatile
        private var instance: MyRoomDatabase? = null
        fun getInstance(context: Context): MyRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context, MyRoomDatabase::class.java, "user_database"
                ).build()
            }
        }
    }

}