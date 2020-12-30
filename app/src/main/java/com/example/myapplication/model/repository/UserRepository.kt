package com.example.myapplication.model.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.model.database.UserAccountDao
import com.example.myapplication.model.entity.UserAccount

class UserRepository private constructor(private val userAccountDao: UserAccountDao) {
    private val userAccountLiveData: LiveData<UserAccount>? = null

    fun isValidAccount(username: String, password: String): LiveData<MutableList<UserAccount>> {

        val userAccount = userAccountDao.getAccount()
        return userAccount
    }

    fun insertUser(username: String, password: String) {
        val account = UserAccount(username, password)
        userAccountDao.insert(account)
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
