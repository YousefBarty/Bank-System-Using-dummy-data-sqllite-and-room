package com.example.todo.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name:String,
    var email: String,
    var current_balance: Double
)
