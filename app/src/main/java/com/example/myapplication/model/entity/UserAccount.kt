package com.example.myapplication.model.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="useraccounts")
class UserAccount() {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="userid")
    var ID:Int?=null

    @ColumnInfo(name="username")
    var username:String? = null

    @ColumnInfo(name="password")
    var password:String? = null

}