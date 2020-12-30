package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.entity.UserApiService
import com.example.myapplication.model.entity.UserList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    private val usersService= UserApiService()
    private val disposable= CompositeDisposable()
    val users= MutableLiveData<List<UserList>>()
    val usersLoadError= MutableLiveData<Boolean>()
    val loading= MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value=true
        disposable.add(
            usersService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<UserList>>(){
                    override fun onSuccess(userList: List<UserList>) {
                      users.value=userList
                        usersLoadError.value=false
                        loading.value=false
                    }

                    override fun onError(e: Throwable) {
                        usersLoadError.value=true
                        loading.value=false
                        e.printStackTrace()
                    }

                }
        )
        )
    }
    override fun onCleared(){
        super.onCleared()
        disposable.clear()

    }
}