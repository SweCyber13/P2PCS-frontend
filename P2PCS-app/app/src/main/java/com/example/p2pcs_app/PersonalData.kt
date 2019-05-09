package com.example.p2pcs_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class PersonalData : AppCompatActivity() {
    private var name: TextView? = null
    private var surname: TextView? = null
    private var username:TextView?=null
    private var email:TextView?=null
    private var password:TextView?=null
    private var age:TextView?=null
    private var sex:TextView?=null
    private var city:TextView?=null
    private var drivingLicense:TextView?=null
    private var dateDrivingLicense:TextView?=null
    private var job:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        name = findViewById<TextView>(R.id.Name)
        surname= findViewById<TextView>(R.id.Surname)
        username=findViewById<TextView>(R.id.Username)
        email=findViewById<TextView>(R.id.Email)
        password=findViewById<TextView>(R.id.Password)
        age=findViewById<TextView>(R.id.Age)
        sex=findViewById<TextView>(R.id.Sex)
        city=findViewById<TextView>(R.id.City)
        drivingLicense=findViewById<TextView>(R.id.DrivingLicense)
        dateDrivingLicense=findViewById<TextView>(R.id.DateDrivingLicense)
        job=findViewById<TextView>(R.id.Job)
        getRequiredData()
        getOptionalData()
    }

    // function for network call
    fun getRequiredData() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/RequiredData.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_name: String = ""
                var str_surname: String =""
                var str_username: String=""
                var str_email: String=""
                var str_password: String=""
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_name =  "\n" + jsonInner.get("Nome")
                    str_surname= "\n" + jsonInner.get("Cognome")
                    str_username= "\n" + jsonInner.get("Username")
                    str_email= "\n" + jsonInner.get("Mail")
                    str_password= "\n" + jsonInner.get("Password")

                }
                name!!.text = "$str_name "
                surname!!.text="$str_surname"
                username!!.text="$str_username"
                email!!.text="$str_email"
                password!!.text="$str_password"
            },
            Response.ErrorListener {
                name!!.text = it.toString()
                surname!!.text= it.toString()
                username!!.text= it.toString()
                email!!.text= it.toString()
                password!!.text= it.toString()

            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER","Ele")
                return params
            }
        }

        queue.add(stringReq)
    }
    fun getOptionalData() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/OptionalData.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_age: String = ""
                var str_sex: String = ""
                var str_city: String = ""
                var str_driving_license: String = ""
                var str_date_driving_license: String = ""
                var str_job: String = ""


                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_age =  "\n" + jsonInner.get("Eta")
                    str_sex =  "\n" + jsonInner.get("Sesso")
                    str_city =  "\n" + jsonInner.get("Citta")
                    str_driving_license =  "\n" + jsonInner.get("Numero_patente")
                    str_date_driving_license =  "\n" + jsonInner.get("Data_rilascio_p")
                    str_job="\n" + jsonInner.get("Occupazione")

                }
                age!!.text = "$str_age"
                sex!!.text = "$str_sex"
                city!!.text = "$str_city"
                drivingLicense!!.text = "$str_driving_license"
                dateDrivingLicense!!.text = "$str_date_driving_license"
                job!!.text = "$str_job"

            },
            Response.ErrorListener {
                age!!.text = it.toString()
                sex!!.text = it.toString()
                city!!.text = it.toString()
                drivingLicense!!.text = it.toString()
                dateDrivingLicense!!.text = it.toString()
                job!!.text = it.toString()



            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER","Ele")
                return params
            }
        }

        queue.add(stringReq)
    }
}


//textView!!.text = "That didn't work!"










/*import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class volley_test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_test)
    }
}*/
