package com.example.roomdatabaseexample.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaseexample.model.EmployeeEntity


@Database(entities = [EmployeeEntity::class], version = 1)
abstract class RoomAppDb : RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao

    companion object {
        private var INSTANCE: RoomAppDb? = null
        fun getAppDatabase(context: Context): RoomAppDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, RoomAppDb::class.java, "AppDb")
                    .allowMainThreadQueries().build();
            }
            return INSTANCE
        }
    }
}