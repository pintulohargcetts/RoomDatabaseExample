package com.example.roomdatabaseexample.repository.db

import androidx.room.*
import com.example.roomdatabaseexample.model.EmployeeEntity

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employeetable ORDER BY id DESC")
    fun getAllEmployeeInfo(): List<EmployeeEntity>?

    @Insert
    fun insertEmployee(employee: EmployeeEntity?)


    @Update
    fun updateEmployee(employee: EmployeeEntity?)

    @Delete
    fun deleteEmployee(employee: EmployeeEntity?)
}