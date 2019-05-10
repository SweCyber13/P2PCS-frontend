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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.DatabaseException
import com.example.p2pcs_app.MainActivity
import com.example.p2pcs_app.R
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

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

                    //manda alla conferma
                    val intent= Intent(context, ConfirmRegisterActivity::class.java)
                    startActivity(intent)

                    //make volley request for insert user in background (maybe not in this activity???)
                    try{
                        saveUser(context,username,password,name,surname,email)
                    }
                    catch(e: DatabaseException){
                        //delete cognito user perchè c'è stato un errore
                        (user as CognitoUser).deleteUser(null)
                        Toast.makeText(context, "Si è verificato un errore" , Toast.LENGTH_LONG).show()
                    }


                } else {
                    //utente confermato manda al login, non dovrebbe mai succedere
                    val intent= Intent(context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //clear all previous activities
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
        val cognitoSettings: CognitoSettings = CognitoSettings(context)
        cognitoSettings.getUserPool().signUpInBackground(username,password,userattributes,null,signupCallback)

    }

    fun saveUser(context: Context, username: String, password: String, name: String, surname: String, email: String){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/registrazione.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                if(strResp!="success")
                {
                    throw(DatabaseException("errore"))
                }
            },
            Response.ErrorListener {
                throw(DatabaseException("errore"))
            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USERNAME",username)
                params.put("NOME",name)
                params.put("COGNOME",surname)
                params.put("EMAIL",email)
                params.put("PASSWORD",password)
                return params
            }
        }

        queue.add(stringReq)
    }





}
