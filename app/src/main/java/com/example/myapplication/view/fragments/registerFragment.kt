package com.example.myapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.util.ValidationUtil
import com.example.myapplication.viewmodel.UserViewModel


class registerFragment : Fragment(R.layout.fragment_register) {

    private var userViewModel: UserViewModel? = null
    private var usernameText: EditText? = null
    private var passwordText: EditText? = null
    private var cpasswordText: EditText? = null
    private var signUpButton: Button? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameText = view.findViewById(R.id.input_email)
        passwordText = view.findViewById<View>(R.id.input_password) as EditText
        cpasswordText = view.findViewById<View>(R.id.confirminput_password) as EditText
        signUpButton = view.findViewById<View>(R.id.link_signup) as Button

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this)).get(UserViewModel::class.java)

        signUpButton!!.setOnClickListener {
            if (ValidationUtil.isValidUsername(this, usernameText.toString())) {
                if (ValidationUtil.isValidPassword(this, passwordText.toString())) {
                    if (ValidationUtil.isValidConfirmPassword(this, passwordText.toString())) {
                        if (ValidationUtil.ismatchPassword(this, passwordText.toString(),cpasswordText.toString())) {
                            userViewModel!!.createUser(
                                usernameText!!.text.toString(),
                                passwordText!!.text.toString()
                            )
                            ValidationUtil.showToast(this, "Successfully Created An Account!")
                        }
                    }
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)


    }
}



