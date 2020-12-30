package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.util.ValidationUtil
import com.example.myapplication.viewmodel.UserViewModel
import com.example.myapplication.viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*


class loginFragment : Fragment(R.layout.fragment_login) {
    private var userViewModel: UserViewModel? = null
    private var usernameText: EditText? = null
    private var passwordText: EditText? = null
    private var signUpButton: Button? = null
    private var loginButton: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameText = view.findViewById(R.id.input_email)
        passwordText = view.findViewById<View>(R.id.input_password) as EditText
        loginButton = view.findViewById<View>(R.id.btn_login) as Button
        btn_register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
        loginButton!!.setOnClickListener {
            // userViewModel!!.checkValidLogin(usernameText!!.text.toString(), passwordText!!.text.toString())
            // ValidationUtil.showToast(getApplicationContext(),"Successfully Logged in!")
            btn_login.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_listFragment)}
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    }

