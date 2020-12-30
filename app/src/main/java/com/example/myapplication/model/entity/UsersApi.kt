package com.example.myapplication.model.entity
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    fun getUsers():Single<List<UserList>>
}