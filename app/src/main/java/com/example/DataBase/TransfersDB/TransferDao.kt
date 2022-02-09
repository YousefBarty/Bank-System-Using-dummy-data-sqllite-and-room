package com.example.todo.DataBase

import androidx.room.*

@Dao
interface TransferDao {

    @Insert
    public fun addTransfersOP(transferOP: TransfersData)

    @Query("Select * From TransfersData")
    public fun getAllTransfersOP():List<TransfersData>

}