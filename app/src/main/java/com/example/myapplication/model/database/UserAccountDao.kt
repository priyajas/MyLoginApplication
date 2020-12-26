package com.example.myapplication.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.entity.UserAccount

@Dao
interface UserAccountDao {
    @Insert
    fun insert(account: UserAccount)

    @Query("SELECT * FROM useraccounts WHERE useraccounts.username LIKE :username ")
    fun getAccount(username: String): UserAccount
}