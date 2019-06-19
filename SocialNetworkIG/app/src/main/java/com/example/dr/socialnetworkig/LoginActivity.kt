package com.example.dr.socialnetworkig

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private var LoginButton: Button? = null
    private var UserEmail: EditText? = null
    private var UserPassword: EditText? = null
    private var NewAccountLink: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        NewAccountLink = findViewById<View>(R.id.register_account_link) as TextView
        UserEmail = findViewById<View>(R.id.login_email) as EditText
        UserPassword = findViewById<View>(R.id.login_password) as EditText
        LoginButton = findViewById<View>(R.id.login_button) as Button

        NewAccountLink!!.setOnClickListener { SendUserToRegister() }
    }

    private fun SendUserToRegister() {
        val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(registerIntent)
       // finish()
    }
}
