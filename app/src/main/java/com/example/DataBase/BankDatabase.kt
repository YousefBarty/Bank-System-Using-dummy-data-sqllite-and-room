package com.example.todo.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CustomersData::class,TransfersData::class], version = 1)
abstract class bankDB : RoomDatabase(){

    abstract fun customerDao(): CustomersDao
    abstract fun transferDao(): TransferDao

    companion object {

        val appDatabaseName = "AppDataBase"


        @Volatile
        private var instance: bankDB? = null


        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context, bankDB::class.java, appDatabaseName)
            .createFromAsset("database/CustomersData.db")
            .allowMainThreadQueries()
            .build()
    }

}