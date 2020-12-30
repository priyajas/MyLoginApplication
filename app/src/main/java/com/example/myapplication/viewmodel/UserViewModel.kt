package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.database.UserAccountDatabase
import com.example.myapplication.model.entity.UserAccount
import com.example.myapplication.model.repository.UserRepository

class UserViewModel(context:Context) : ViewModel() {

    private val userRepository: UserRepository

    init {

        userRepository = UserRepository.getInstance(UserAccountDatabase.getAppDatabase(context).userAccountDao())
    }

    internal fun createUser(username: String, password: String) {
        userRepository.insertUser(username, password)
    }

    internal fun checkValidLogin(username: String, password: String): LiveData<MutableList<UserAccount>> {
        return userRepository.isValidAccount(username, password)
    }

    class Factory internal constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(ctxt) as T
        }
    }
}
