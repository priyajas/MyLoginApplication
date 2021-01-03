package com.example.myapplication.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.entity.UserAccount


@Dao
interface UserAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userAccount: UserAccount):Long

    @Query("SELECT * FROM useraccounts where  username like :username and password like :password")
    open fun getAccount(username: String, password: String): LiveData<MutableList<UserAccount>>


}