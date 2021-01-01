package com.example.roomdatabaseexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.EmployeeEntity
import com.example.roomdatabaseexample.ui.adaptor.RecyclerViewAdaptor
import com.example.roomdatabaseexample.viewmodels.MainActivity2ViewModel

class MainActivity2 : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var employeeListViewAdapter: RecyclerViewAdaptor;
    lateinit var editTextName: EditText
    lateinit var editTextEmail: EditText
    lateinit var emptyView: View
    lateinit var saveButton: Button

    lateinit var viewModel: MainActivity2ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        editTextName = findViewById(R.id.employeeNameInputView);
        editTextEmail = findViewById(R.id.employeeEmailInputView)
        saveButton = findViewById(R.id.addButtonView)
        recyclerView = findViewById(R.id.recyclerView)
        emptyView = findViewById(R.id.noEmployeeView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            employeeListViewAdapter = RecyclerViewAdaptor().setListener(object : RecyclerViewAdaptor.OnItemClickedListener {
                override fun onEmployeeDeletedClicked(employee: EmployeeEntity) {
                    viewModel.delete(employee)
                }
                override fun onEmployeeClicked(employee: EmployeeEntity) {
                    editTextName.setText(employee.employeename)
                    editTextEmail.setText(employee.email)
                    editTextName.setTag(editTextName.id, employee.id)
                    saveButton.setText(getString(R.string.update_employee))
                }
            })
            adapter = employeeListViewAdapter
            // add divider
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }


        viewModel = ViewModelProvider(this).get(MainActivity2ViewModel::class.java)
        viewModel.getObserverLiveData().observe(this, object : Observer<List<EmployeeEntity>> {
            override fun onChanged(t: List<EmployeeEntity>?) {
                employeeListViewAdapter.run {
                    setArrayList(ArrayList(t))
                    t?.run {
                        if (isEmpty()){
                            emptyView.visibility = View.VISIBLE
                            saveButton.setText(getString(R.string.add_employee))
                        }else{
                            emptyView.visibility = View.GONE
                        }
                    }
                    notifyDataSetChanged()
                }
            }
        }
        )
        viewModel.getAllEmployee()
        saveButton.setOnClickListener {
            val username = editTextName.text.toString()
            val userEmail = editTextEmail.text.toString()
            if (saveButton.text.equals(getString(R.string.add_employee))) {
                if (!username.equals("") && !userEmail.equals("")) {
                    viewModel.insert(EmployeeEntity(0, employeename = username, email = userEmail))
                }
            } else if (saveButton.text.equals(getString(R.string.update_employee))) {
                if (!username.equals("") && !userEmail.equals("")) {
                    viewModel.update(
                        EmployeeEntity(
                            editTextName.getTag(editTextName.id).toString().toInt(),
                            employeename = username,
                            email = userEmail
                        )
                    )
                    editTextName.setText("")
                    editTextEmail.setText("")
                    saveButton.setText(getString(R.string.add_employee))
                }
            }
        }
    }
}