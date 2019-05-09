package com.example.p2pcs_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class PersonalData : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        val button = findViewById<ImageView>(R.id.provapopup)
        val fragment_manager = supportFragmentManager
        val editnamedialog= EditNameFragment()
        button.setOnClickListener{
            editnamedialog.show(fragment_manager,"changename")
        }
    }

*/
    private var name: TextView? = null
    private var surname: TextView? = null
    private var email: TextView? = null
    private var password: TextView? = null
    private var eta: TextView? = null
    private var sesso: TextView? = null
    private var citta: TextView? = null
    private var numeroPatente: TextView? = null
    private var dataRilascioPatente: TextView? = null
    private var occupazione: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        name = findViewById<TextView>(R.id.Name)
        surname= findViewById<TextView>(R.id.Surname)
        email= findViewById<TextView>(R.id.Email)
        password= findViewById<TextView>(R.id.Password)

        getRequiredData()
        getOptionalData()
    }

    fun getRequiredData() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/RequiredData.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                var str_name: String = ""
                var str_surname: String = ""
                var str_username: String = ""
                var str_email: String = ""
                var str_password: String = ""
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_name = "\n" + jsonInner.get("Nome")
                    str_surname = "\n" + jsonInner.get("Cognome")
                    str_username = "\n" + jsonInner.get("Username")
                    str_email = "\n" + jsonInner.get("Email")
                    str_password = "\n" + jsonInner.get("Password")
                }
                name!!.text = "response : $str_name "
                surname!!.text = "response : $str_surname "
                username!!.text = "response : $str_username "
                email!!.text = "response : $str_email "
                password!!.text = "response : $str_password"

            },
            Response.ErrorListener {
                name!!.text = it.toString()
                surname!!.text = it.toString()
                username!!.text = it.toString()
                email!!.text = it.toString()
                password!!.text = it.toString()

            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER","admin")
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
        val stringReq = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)

                var str_name: String = ""
                var str_surname: String = ""
                var str_username: String = ""
                var str_email: String = ""
                var str_password: String = ""
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_name = "\n" + jsonInner.get("Nome")
                    str_surname = "\n" + jsonInner.get("Cognome")
                    str_username = "\n" + jsonInner.get("Username")
                    str_email = "\n" + jsonInner.get("Email")
                    str_password = "\n" + jsonInner.get("Password")
                }
                name!!.text = "response : $str_name "
                surname!!.text = "response : $str_surname "
                username!!.text = "response : $str_username "
                email!!.text = "response : $str_email "
                password!!.text = "response : $str_password"

            },
            Response.ErrorListener {
                name!!.text = it.toString()
                surname!!.text = it.toString()
                username!!.text = it.toString()
                email!!.text = it.toString()
                password!!.text = it.toString()

            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("USER","admin")
                return params
            }
        }

        queue.add(stringReq)
    }
}
