package com.example.myapplication.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.entity.UserAccount


@Dao
interface UserAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userAccount: UserAccount):Long

    @Query("SELECT * FROM useraccounts where  username =:username and password=:password")
    open fun getAccount(username: String, password:String): UserAccount


}