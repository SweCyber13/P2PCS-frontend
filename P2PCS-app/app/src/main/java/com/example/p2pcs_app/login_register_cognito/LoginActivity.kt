package com.example.p2pcs_app.login_register_cognito

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.auth.CognitoCredentialsProvider
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.amazonaws.regions.Regions
import com.example.p2pcs_app.MainActivity
import com.example.p2pcs_app.R
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //here skip if already logged
        val cognitosettings= CognitoSettings(this)
        cognitosettings.getUserPool().currentUser.signOut()
        /*if(cognitosettings.getUserPool().currentUser!=null) {
            //Crea sharedpreferences con username
            val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
            prefs.edit().putString("username",cognitosettings.getUserPool().currentUser.userId).apply()
            //move to mainactivity
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            //kill login so no returning back
            this.finish()
        }*/

        //user not already logged

        setContentView(R.layout.login)

        //setting listener for login
        val confirmbutton= findViewById<Button>(R.id.confirm)
        confirmbutton.setOnClickListener{
            val username=findViewById<EditText>(R.id.username).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()
            loginUser(this,username,password)
        }

        //listener for opening registration
        val signuptext= findViewById<TextView>(R.id.signup)
        signuptext.setOnClickListener{
            val intent= Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    fun loginUser(context: Context, username:String, password:String) {

        val authenticationHandler = object: AuthenticationHandler{
            override fun onSuccess(userSession: CognitoUserSession?, newDevice: CognitoDevice?) {
                //Crea sharedpreferences con username

                val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)
                prefs.edit().putString("username",(userSession as CognitoUserSession).username).apply()


                //move to mainactivity
                val intent= Intent(context, MainActivity::class.java)
                startActivity(intent)
                //kill login so no returning back
                (context as AppCompatActivity).finish()
            }

            override fun onFailure(exception: Exception?) {
                if(exception!= null)
                    Toast.makeText(context, exception.localizedMessage , Toast.LENGTH_LONG).show()
                    //(context as Activity).findViewById<TextView>(R.id.textView5).text= "fail".plus(exception.localizedMessage)
                else
                    Toast.makeText(context, "MEGA ERRORE" , Toast.LENGTH_LONG).show()
                    //(context as Activity).findViewById<TextView>(R.id.textView5).text= "massive failure"
            }

            override fun authenticationChallenge(continuation: ChallengeContinuation?) {
                //no further authentication actions required
            }

            override fun getAuthenticationDetails(
                authenticationContinuation: AuthenticationContinuation?,
                userId: String?
            ) {
                val authenticationdetails= AuthenticationDetails(username,password,null)
                //continue the authentication passing the params username and password
                (authenticationContinuation as  AuthenticationContinuation).setAuthenticationDetails(authenticationdetails)
                authenticationContinuation.continueTask()
            }

            override fun getMFACode(continuation: MultiFactorAuthenticationContinuation?) {
                //no further authentication actions required
            }
        }


        val cognitosettings= CognitoSettings(context)
        val user= cognitosettings.getUserPool().getUser(username)
        user.getSessionInBackground(authenticationHandler) //waiting for callback


    }





}
