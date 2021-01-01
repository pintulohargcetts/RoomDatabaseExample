package com.example.roomdatabaseexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomdatabaseexample.repository.db.RoomAppDb
import com.example.roomdatabaseexample.model.EmployeeEntity


class MainActivity2ViewModel(app: Application) : AndroidViewModel(app) {
    var allEmployees: MutableLiveData<List<EmployeeEntity>> = MutableLiveData()

    fun getObserverLiveData(): MutableLiveData<List<EmployeeEntity>> {
        return allEmployees
    }

    fun getAllEmployee() {
        val employeeDao = RoomAppDb.getAppDatabase(getApplication())?.getEmployeeDao();
        val list = employeeDao?.getAllEmployeeInfo();
        allEmployees.postValue(list)
    }

    fun insert(employee: EmployeeEntity) {
        val employeeDao = RoomAppDb.getAppDatabase(getApplication())?.getEmployeeDao();
        employeeDao?.insertEmployee(employee)
        getAllEmployee()
    }

    fun update(employee: EmployeeEntity) {
        val employeeDao = RoomAppDb.getAppDatabase(getApplication())?.getEmployeeDao();
        employeeDao?.updateEmployee(employee)
        getAllEmployee()
    }

    fun delete(employee: EmployeeEntity) {
        val employeeDao = RoomAppDb.getAppDatabase(getApplication())?.getEmployeeDao();
        employeeDao?.deleteEmployee(employee)
        getAllEmployee()
    }
}