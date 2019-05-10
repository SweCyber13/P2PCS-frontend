package com.example.p2pcs_app.login_register_cognito

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
import com.example.p2pcs_app.R
import java.lang.Exception

class ConfirmRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmregister)

        //setting listener for confirm
        val confirmbutton= findViewById<Button>(R.id.confirm)
        confirmbutton.setOnClickListener{
            val code=findViewById<EditText>(R.id.confirmationcode).text.toString()
            confirmRegister(this,code)
        }

    }

    fun confirmRegister(context: Context,code: String){

        val confirmcallback: GenericHandler = object : GenericHandler {
            override fun onSuccess() {
                //la registrazione ha avuto successo torno al login
                val intent= Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //clear all previous activities
                startActivity(intent)
            }

            override fun onFailure(exception: Exception?) {
                Toast.makeText(context, "codice errato" , Toast.LENGTH_LONG).show()
            }
        }

        val cognitosettings= CognitoSettings(context)
        //recupero username
        val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val username=prefs.getString("username","vuoto")

        val user= cognitosettings.getUserPool().getUser(username)
        user.confirmSignUpInBackground(code,false,confirmcallback)

    }





}
