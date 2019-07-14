package com.example.p2pcs_app.login_register_cognito.Registration

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amazonaws.mobileconnectors.cognitoidentityprovider.*
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.DatabaseException
import com.example.p2pcs_app.R
import com.example.p2pcs_app.login_register_cognito.CognitoSettings
import com.example.p2pcs_app.login_register_cognito.ConfirmRegistration.ActivityConfirmRegister
import com.example.p2pcs_app.login_register_cognito.Login.ActivityLogin
import java.lang.Exception

class ActivityRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //setting listener for registration
        val confirmbutton= findViewById<Button>(R.id.confirm)
        confirmbutton.setOnClickListener{
            val username=findViewById<EditText>(R.id.username).text.toString()
            val name=findViewById<EditText>(R.id.name).text.toString()
            val surname=findViewById<EditText>(R.id.surname).text.toString()
            val email=findViewById<EditText>(R.id.email).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()
            val confirmpassword=findViewById<EditText>(R.id.confirmPassword).text.toString()

            //DA FARE CONTROLLO PASSWORD
            if(password==confirmpassword) {
                registerUser(this,username,password,name,surname,email)
            }
            else {
                Toast.makeText(this, "Errore: le password inserite non coincidono" , Toast.LENGTH_LONG).show()
            }
        }

    }

    fun registerUser(context: Context, username: String, password: String, name: String, surname: String, email: String) {
        var userattributes=CognitoUserAttributes()

        //Your Java code relies on SAM conversion - an automatic conversion of a lambda into an interface with a single
        // abstract method. SAM conversion is currently not supported for interfaces defined in Kotlin. Instead, you need
        // to define an anonymous object implementing the interface:
        val signupCallback: SignUpHandler= object : SignUpHandler {
            override fun onSuccess(
                user: CognitoUser?,
                signUpConfirmationState: Boolean,
                cognitoUserCodeDeliveryDetails: CognitoUserCodeDeliveryDetails?
            ) {
                //signup was successful

                if(!signUpConfirmationState) {
                    //utente non confermato, da mandare alla schermata e salvare username
                    //aggiunta username a shared preferences
                    val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)
                    prefs.edit().putString("username",(user as CognitoUser).userId).apply()

                    //make volley request for insert user in background (maybe not in this activity???)
                    try{
                        saveUser(context,username,password,name,surname,email)
                    }
                    catch(e: DatabaseException){
                        //delete cognito user perchè c'è stato un errore
                        (user as CognitoUser).deleteUser(null)
                        Toast.makeText(context, "Si è verificato un errore" , Toast.LENGTH_LONG).show()
                    }

                    //manda alla conferma
                    val intent= Intent(context, ActivityConfirmRegister::class.java)
                    startActivity(intent)


                } else {
                    //utente confermato manda al activity_login, non dovrebbe mai succedere
                    val intent= Intent(context, ActivityLogin::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //clear all previous activities
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }

            }

            override fun onFailure(exception: Exception?) {
                //signup failed search for the causes
                if(exception!= null)
                    Toast.makeText(context, "Errore: ".plus(exception.localizedMessage) , Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(context, "Si è verificato un errore" , Toast.LENGTH_LONG).show()
            }
        }

        //aggiungo la mail che deve essere univoca
        userattributes.addAttribute("email",email)
        //connetto ad aws e faccio partire la procedura di registrazione, verrà mandata una mail con codice se ha successo
        val cognitoSettings: CognitoSettings =
            CognitoSettings(context)
        cognitoSettings.getUserPool().signUpInBackground(username,password,userattributes,null,signupCallback)

    }

    fun saveUser(context: Context, username: String, password: String, name: String, surname: String, email: String){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/user/create.php?USERNAME="+username+"&NAME="+name+"&SURNAME="+surname+"&MAIL="+email

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                if(strResp!="meesage->Success")
                {
                    //throw(DatabaseException("errore"))
                }
            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }





}
