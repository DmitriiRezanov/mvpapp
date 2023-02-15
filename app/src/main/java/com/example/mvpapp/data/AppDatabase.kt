package com.example.mvpapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object{

        private  var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "shop_item.db"

        fun getInstance(application: Application): AppDatabase { //Паттерн синглтон, один экземпляр во всем приложении
            INSTANCE?.let { //если экземпляр БД есть, то возвращаем его
                return it
            }
            synchronized(LOCK){ // double check если 2 потока одновременно попытаютя обратиться к БД, то один встанет в очередь
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}