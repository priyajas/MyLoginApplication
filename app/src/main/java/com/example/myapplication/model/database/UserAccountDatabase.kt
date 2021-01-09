package com.example.myapplication.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.entity.UserAccount


@Database(entities = arrayOf(UserAccount::class), version = 1)
abstract class UserAccountDatabase : RoomDatabase() {

    abstract fun userAccountDao(): UserAccountDao

    companion object {
        private var INSTANCE: UserAccountDatabase? = null

        fun getAppDatabase(context: Context): UserAccountDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, UserAccountDatabase::class.java, "user-database").allowMainThreadQueries().build()

            }

            return INSTANCE!!

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}