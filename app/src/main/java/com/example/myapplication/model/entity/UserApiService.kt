package com.example.myapplication.model.entity

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserApiService {

    private val BASE_URL="https://jsonplaceholder.typicode.com"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UsersApi::class.java)

    fun getUsers(): Single<List<UserList>> {
        return api.getUsers()
    }

}