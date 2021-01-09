package com.example.myapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.util.ValidationUtil
import com.example.myapplication.viewmodel.UserViewModel
import com.example.myapplication.viewmodel.UserViewModelFactory


class loginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var userViewModel: UserViewModel
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private var globalContext: Context? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameText = view.findViewById(R.id.input_email)
        passwordText = view.findViewById<View>(R.id.input_password) as EditText
        loginButton = view.findViewById<View>(R.id.btn_login) as Button
        registerButton = view.findViewById<View>(R.id.btn_register) as Button
        registerButton!!.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        loginButton!!.setOnClickListener {
            if (globalContext?.let { it1 ->
                    ValidationUtil.isValidUsername(
                        it1,
                        usernameText.text.toString()
                    )
                }!!) {
                if (ValidationUtil.isValidPassword(
                        globalContext as FragmentActivity,
                        passwordText.text.toString()
                    )
                ) {

                    println("username is ${usernameText.text.toString()}")
                    println("password is ${passwordText.text.toString()}")
                    val userdata = userViewModel!!.checkValidLogin(
                        usernameText!!.text.toString(),
                        passwordText!!.text.toString()
                    )
                    println("userdata is $userdata")
                    if (userdata!=null) {
                        ValidationUtil.showToast(
                            globalContext as FragmentActivity,
                            "Successfully Logged in!"
                        )
                        findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                    } else {
                        ValidationUtil.showToast(
                            globalContext as FragmentActivity,
                            "Invalid Credentials...Please try again"
                        )
                    }


                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalContext = this.getActivity();
     // userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel = ViewModelProviders.of(this, globalContext?.let { UserViewModelFactory(it) }).get(UserViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    }

