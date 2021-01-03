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


class registerFragment : Fragment(R.layout.fragment_register) {

    private var userViewModel: UserViewModel? = null
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private lateinit var cpasswordText: EditText
    private lateinit var signUpButton: Button
    private var globalContext: Context?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameText = view.findViewById(R.id.input_email)
        passwordText = view.findViewById<View>(R.id.input_password) as EditText
        cpasswordText = view.findViewById<View>(R.id.confirminput_password) as EditText
        signUpButton = view.findViewById<View>(R.id.link_signup) as Button
        signUpButton!!.setOnClickListener {

            if (globalContext?.let { it1 -> ValidationUtil.isValidUsername(it1, usernameText.text.toString()) }!!) {
                if (ValidationUtil.isValidPassword(globalContext as FragmentActivity, passwordText.text.toString())) {
                    if (ValidationUtil.isValidConfirmPassword(globalContext as FragmentActivity, passwordText.text.toString())) {
                        if (ValidationUtil.ismatchPassword(globalContext as FragmentActivity, passwordText.text.toString(),cpasswordText.text.toString())) {
                           val accountresult=userViewModel!!.createUser(
                                usernameText!!.text.toString(),
                                passwordText!!.text.toString()
                            )
                            print("accountresult is $accountresult")
                            if(accountresult.equals(null)){
                                ValidationUtil.showToast(
                                    globalContext as FragmentActivity,
                                    "Account not Created..!! Try Again")
                            }else {
                                ValidationUtil.showToast(
                                    globalContext as FragmentActivity,
                                    "Successfully Created An Account!"
                                )
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                        }
                    }
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalContext = this.getActivity();
        userViewModel = ViewModelProviders.of(this, globalContext?.let { UserViewModelFactory(it) }).get(UserViewModel::class.java)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)


    }
}



