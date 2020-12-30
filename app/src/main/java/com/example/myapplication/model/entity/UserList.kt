package com.example.myapplication.model.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserList(
    @SerializedName("id")
    var userid:String?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("username")
    var username:String?,
    @SerializedName("email")
    var email:String?,
    @SerializedName("address")
    var address:Address,
    @SerializedName("phone")
    var phone:String?,
    @SerializedName("website")
    var website:String?,
    @SerializedName("company")
    var company:Company
)
data class Address(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String,
    @SerializedName("geo") val geo: Geo
)

data class Geo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)

data class Company(
    @SerializedName("name") val name: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val bs: String
)