package com.example.bankinigsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.todo.DataBase.CustomersData
import com.example.todo.DataBase.TransfersData
import com.example.todo.DataBase.bankDB

class Transfer_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_)

        var id_reciver: Int = -1
        var email_reciver:String=""
        var money_transfer = findViewById<EditText>(R.id.money_transfer)
        var transfer_btn = findViewById<Button>(R.id.transfer_btn)
        val spinner = findViewById<Spinner>(R.id.spinner)
        var customer_dao = bankDB(this).customerDao()
        var transfer_dao= bankDB(this).transferDao()

        var active_customer = intent.getParcelableExtra<CustomerDetailsData>(EXTRA_DETAILS)

        var allcustomersExcept = bankDB(context = this).customerDao().getAllCustomers()

        var Customers_reciver = mutableListOf<String>()
        var Customers_reciver_IDs = mutableListOf<Int>()

        for (i in allcustomersExcept) {
            if (active_customer!!.id != i.id) {
                Customers_reciver_IDs.add(i.id)
                Customers_reciver.add(i.name)
            }
        }
        if (spinner != null) {
            val adapter = ArrayAdapter(this, R.layout.spinner_item, Customers_reciver)
            spinner.adapter = adapter
        }
        transfer_btn.setOnClickListener {

            if (!TextUtils.isEmpty(money_transfer.text)) {

                if (active_customer?.current_balance!! >= money_transfer.text.toString()
                        .toDouble()
                ) {

                    var new = active_customer?.current_balance!! - money_transfer.text.toString()
                        .toDouble()
                    for (i in allcustomersExcept) {
                        if (id_reciver == i.id) {
                            email_reciver=i.email
                            var recivier_customer_balance = CustomersData(
                                id = i.id,
                                name = i.name,
                                email = i.email,
                                current_balance = i.current_balance + money_transfer.text.toString()
                                    .toDouble()
                            )
                            customer_dao.updateCustomer(recivier_customer_balance)
                            break
                        }
                    }
                    var sender_customer_balance = CustomersData(
                        id = active_customer.id,
                        name = active_customer.name.toString(),
                        email = active_customer.email.toString(),
                        current_balance = new
                    )
                    customer_dao.updateCustomer(sender_customer_balance)
                    var transfer_OP=TransfersData(
                        id_sender = active_customer.id,
                        id_reciver = id_reciver,
                        email_sender = active_customer.email.toString(),
                        email_reciver = email_reciver,
                        money_transfer = money_transfer.text.toString().toDouble()

                    )
                    transfer_dao.addTransfersOP(transfer_OP)
                    finish()
                } else {
                    money_transfer.setError("Please Enter correct balance")
                }
            } else {
                money_transfer.setError("Please Enter balance")
            }
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                id_reciver = Customers_reciver_IDs[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}
