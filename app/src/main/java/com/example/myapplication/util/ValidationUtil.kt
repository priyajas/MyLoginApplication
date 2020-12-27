package com.example.myapplication.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

object ValidationUtil {

    public fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    public fun isNullOrEmpty(input: String?): Boolean = input == null || input.isEmpty()

    public fun isValidUsername(context: Context, username: String?, regex: String = "^[a-zA-Z0-9._-]{3,20}$"): Boolean {
        when {
            isNullOrEmpty(username) -> showToast(context, "Please enter User name first.")
            !Pattern.matches(regex, username) -> showToast(context, "Please enter a valid User name.")
            else -> return true
        }
        return false
    }


    public fun isValidPassword(context: Context, password: String?): Boolean {
        when {
            isNullOrEmpty(password) -> showToast(context, "Please enter Password first.")
            password!!.length < 6 -> showToast(context, "Password length should not be less than 6 characters")
            password.length > 30 -> showToast(context, "Password length should not be greater than 30 characters")
            else -> return true
        }
        return false
    }
    public fun isValidConfirmPassword(context: Context, confirmpassword: String?): Boolean {
        when {
            isNullOrEmpty(confirmpassword) -> showToast(context, "Please enter Confirm Password first.")
            confirmpassword!!.length < 6 -> showToast(context, "Confirm Password length should not be less than 6 characters")
            confirmpassword.length > 30 -> showToast(context, "Confirm Password length should not be greater than 30 characters")
            else -> return true
        }
        return false
    }

    public fun ismatchPassword(context: Context, password: String?,confirmpassword:String?): Boolean {
        when {
            isNullOrEmpty(password) -> showToast(context, "Please enter Password first.")
            isNullOrEmpty(confirmpassword)-> showToast(context, "Please enter Password first.")
            password!=confirmpassword -> {
                showToast(context, "Password and Confirm Password doesn't match")
            }
            else -> return true
        }
        return false
    }
}
