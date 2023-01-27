package com.example.coopdemo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Get the register button
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        //Register button on click creates an account.
        registerButton.setOnClickListener { createAccount() }

    }

    private fun createAccount(){

        //Getting the email and password the user want to register with.
        val email = findViewById<EditText>(R.id.editTextRegisterEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editTextRegisterPassword).text.toString()

        //Realistically you would want to do some username or password validation here.

        auth = Firebase.auth
        //The function thats called from firebase auth to actually make a user.
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information.
                    Log.d("Register", "createUserWithEmail:success")
                    //Takes you back to the login screen after signing up.
                    val newActivityIntent = Intent(this, LoginActivity::class.java)
                    //user feedback from signing up.
                    newActivityIntent.putExtra("success", true)
                    startActivity(newActivityIntent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Registration failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        /*Some cool things to note:
        * There are many more functions avaliable, like deleting, updating etc.
        *
        * Its also insanely easy to implement an email message that needs to be clicked to
        * Register.
        * 
        * We didn't set up a database to implement this.*/
    }
}