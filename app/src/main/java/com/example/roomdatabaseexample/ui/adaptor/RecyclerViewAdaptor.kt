package com.example.roomdatabaseexample.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.model.EmployeeEntity

open class RecyclerViewAdaptor() :
    RecyclerView.Adapter<RecyclerViewAdaptor.MyViewHolder>() {
    interface OnItemClickedListener {
        fun onEmployeeDeletedClicked(employee: EmployeeEntity)
        fun onEmployeeClicked(employee: EmployeeEntity)
    }

    fun setListener(listener: OnItemClickedListener):RecyclerViewAdaptor{
        uiListener = listener
        return this
    }

    var items = ArrayList<EmployeeEntity>()
    lateinit var uiListener: OnItemClickedListener
    fun setArrayList(data: ArrayList<EmployeeEntity>) {
        this.items = data;
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.employeeNameView);
        val email: TextView = view.findViewById(R.id.employeeEmailView);
        val delete: ImageView = view.findViewById(R.id.deleteEmployeeView);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener { uiListener.onEmployeeClicked(items.get(position)); }
        holder.delete.setOnClickListener { uiListener.onEmployeeDeletedClicked(items.get(position)) }
        holder.tvName.text = items[position].employeename
        holder.email.text = items[position].email
    }
    override fun getItemCount(): Int {
        return items.size
    }
}
