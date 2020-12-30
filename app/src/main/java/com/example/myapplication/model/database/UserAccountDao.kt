package com.example.myapplication.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.entity.UserAccount


@Dao
interface UserAccountDao {
    @Insert
    fun insert(account: UserAccount)

    @Query("SELECT * FROM useraccounts")
    open fun getAccount(): LiveData<MutableList<UserAccount>>
}