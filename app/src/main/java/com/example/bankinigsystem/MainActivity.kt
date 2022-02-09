package com.example.bankinigsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.Adapter.CustomerAdapter
import com.example.todo.DataBase.CustomersData
import com.example.todo.DataBase.bankDB


class MainActivity : AppCompatActivity() , CustomerAdapter.OnItemClickListerner{

    lateinit var customer_Recyceler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customer_Recyceler = findViewById(R.id.customers_Recycler)

//        var customer1 = CustomersData(email = "YousefBarty",name = "Yousef",current_balance = 1000.0)
//        var customer2 = CustomersData(email = "AmirFekry",name = "Amir",current_balance = 1250.0)
//        var customer3 = CustomersData(email = "IshacLobos",name = "Ishac",current_balance = 4050.0)
//        var customer4 = CustomersData(email = "WaseemKM",name = "Waseem",current_balance = 6200.0)
//
//        var dao = bankDB(this).customerDao()
//        dao.addCustomer(customer1)
//        dao.addCustomer(customer2)
//        dao.addCustomer(customer3)
//        dao.addCustomer(customer4)
//
//
//        Toast.makeText(
//            this@MainActivity,
//            "Customer added successfully",
//            Toast.LENGTH_LONG)
        }




    override fun onResume() {
        super.onResume()
        var data = bankDB(context = this).customerDao().getAllCustomers()
        customer_Recyceler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomerAdapter(data,this@MainActivity)
            customer_Recyceler.adapter = adapter
        }
    }

    override fun OnItemClicked(adapterPosition: CustomersData) {
        var Transfer_Activity_Intent = Intent(this, Transfer_Activity::class.java)
        var customerDetails = CustomerDetailsData(0,"", "",0.0)
        customerDetails.id = adapterPosition.id
        customerDetails.name = adapterPosition.name
        customerDetails.email=adapterPosition.email
        customerDetails.current_balance=adapterPosition.current_balance
        Transfer_Activity_Intent.putExtra(EXTRA_DETAILS, customerDetails)
        startActivity(Transfer_Activity_Intent)     }

}