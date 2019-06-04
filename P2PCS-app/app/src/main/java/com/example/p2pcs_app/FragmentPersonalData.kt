package com.example.p2pcs_app

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_personal_data.*
import kotlinx.android.synthetic.main.fragment_edit_name.view.*
import org.json.JSONArray
import org.json.JSONObject

class FragmentPersonalData : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_personal_data, null)

        getRequiredData(requireContext())
        getOptionalData(requireContext())

        return view
    }






    // function for network call
    fun getRequiredData(context: Context) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/RequiredData.php"
        val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)

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

                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_name= "" + jsonInner.get("Nome")
                    str_surname= "" + jsonInner.get("Cognome")
                    str_username= "" + jsonInner.get("Username")
                    str_email= "" + jsonInner.get("Mail")


                }
                Name!!.text = "$str_name "
                Surname!!.text="$str_surname"
                Username!!.text="$str_username"
                Email!!.text="$str_email"

            },
            Response.ErrorListener {
                Name!!.text = it.toString()
                Surname!!.text= it.toString()
                Username!!.text= it.toString()
                Email!!.text= it.toString()


            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER",prefs.getString("username","vuoto"))
                return params
            }
        }

        queue.add(stringReq)
    }
    fun getOptionalData(context: Context) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/OptionalData.php"
        val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)

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
                    str_age =  "" + jsonInner.get("Eta")
                    str_sex =  "" + jsonInner.get("Sesso")
                    str_city =  "" + jsonInner.get("Citta")
                    str_driving_license =  "" + jsonInner.get("Numero_patente")
                    str_date_driving_license =  "" + jsonInner.get("Data_rilascio_p")
                    str_job="" + jsonInner.get("Occupazione")

                }
                Age!!.text = "$str_age"
                Sex!!.text = "$str_sex"
                City!!.text = "$str_city"
                DrivingLicense!!.text = "$str_driving_license"
                DateDrivingLicense!!.text = "$str_date_driving_license"
                Job!!.text = "$str_job"

            },
            Response.ErrorListener {
                Age!!.text = it.toString()
                Sex!!.text = it.toString()
                City!!.text = it.toString()
                DrivingLicense!!.text = it.toString()
                DateDrivingLicense!!.text = it.toString()
                Job!!.text = it.toString()



            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER",prefs.getString("username","vuoto"))
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
