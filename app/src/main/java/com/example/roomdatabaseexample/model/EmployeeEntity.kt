package com.example.roomdatabaseexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employeetable")
class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "employeename")
    val employeename: String,

    @ColumnInfo(name = "email")
    val email: String? = null
)





