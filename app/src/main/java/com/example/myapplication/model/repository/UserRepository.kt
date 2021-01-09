package com.example.myapplication.model.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.model.database.UserAccountDao
import com.example.myapplication.model.entity.UserAccount

class UserRepository private constructor(private val userAccountDao: UserAccountDao) {
    private val userAccountLiveData: LiveData<UserAccount>? = null

    fun isValidAccount(username: String, password: String): UserAccount {
        val result=userAccountDao.getAccount(username,password)
        println("Result is $result")
        return result
    }

    fun insertUser(username: String, password: String): Long {
       val account = UserAccount()
        account.username=username
        account.password=password
        return userAccountDao.insert(account)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userAccountDao: UserAccountDao): UserRepository {
            if (instance == null) {
                instance =
                    UserRepository(
                        userAccountDao
                    )
            }
            return instance!!
        }
    }
}
