package com.example.coopdemo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //Initializing.
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Button on click plays the sign in function.
        findViewById<Button>(R.id.buttonLogIn).setOnClickListener { signIn() }

        val registerLink = findViewById<TextView>(R.id.registerLink)
        registerLink.setOnClickListener {
            val newActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(newActivityIntent)

        }
    }

    private fun signIn(){

        //Get the users email and password they entered.
        val email = findViewById<EditText>(R.id.editTextLoginEmailAddress).text.toString()
        val password = findViewById<EditText>(R.id.editTextLoginPassword).text.toString()

        //Declaring auth.
        auth = Firebase.auth
        //This is the actual sign in code.
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, take user to next activity.
                    Log.d("Login", "signInWithEmail:success")
                    //You would place an intent here to go somewhere.
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}