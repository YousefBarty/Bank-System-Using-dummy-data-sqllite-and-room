package com.example.todo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankinigsystem.R
import com.example.todo.DataBase.CustomersData

class CustomerAdapter(val data: List<CustomersData>, private val listener:OnItemClickListerner) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)

        return CustomerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {

        var CustomerModel = data.get(position)

        holder.name.text = CustomerModel.name
        holder.email.text = CustomerModel.email
        holder.balance.text = CustomerModel.current_balance.toString()


    }

    override fun getItemCount(): Int {

        return data.size
    }


   inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var name: TextView = itemView.findViewById(R.id.customer_name)
        var email: TextView = itemView.findViewById(R.id.customer_email)
       var balance: TextView = itemView.findViewById(R.id.customer_current_balance)


       init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.OnItemClicked(data[adapterPosition])
        }


   }

    interface OnItemClickListerner
    {
        fun OnItemClicked(adapterPosition: CustomersData)
    }

}