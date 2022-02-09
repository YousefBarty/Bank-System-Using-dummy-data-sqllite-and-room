package com.example.todo.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransfersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email_sender: String,
    var email_reciver: String,
    val id_sender: Int,
    val id_reciver: Int,
    var money_transfer: Double
)
