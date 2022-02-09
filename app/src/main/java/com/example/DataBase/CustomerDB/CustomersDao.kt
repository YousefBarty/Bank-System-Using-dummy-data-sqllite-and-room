package com.example.todo.DataBase

import androidx.room.*

@Dao
interface CustomersDao {

    @Insert
    public fun addCustomer(customerData: CustomersData)

    @Query("Select * From CustomersData")
    public fun getAllCustomers():List<CustomersData>

    @Query("Select name From CustomersData")
    public fun getCustomersNames():List<String>

    @Update
    public fun updateCustomer(customerData: CustomersData)


}