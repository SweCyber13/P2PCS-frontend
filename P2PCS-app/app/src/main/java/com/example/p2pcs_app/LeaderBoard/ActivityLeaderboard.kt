package com.example.p2pcs_app.LeaderBoard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import org.json.JSONArray
import org.json.JSONObject

class LeaderboardActivity : AppCompatActivity() {
    private var classificaRank: TextView? = null
    private var posizione: TextView? = null
    private var punteggio: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboards)
        classificaRank = findViewById<TextView>(R.id.username)
        posizione= findViewById<TextView>(R.id.position)
        punteggio=findViewById<TextView>(R.id.rank)

        getBoard()
    }

    // function for network call
    fun getBoard() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/classifica.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_classificarank: String = ""
                var str_posizione: String = ""
                var str_punteggio: String = ""
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_posizione=str_posizione+"\n"+ (i+1)
                    str_classificarank = str_classificarank +"\n" + jsonInner.get("Username")
                    str_punteggio=str_punteggio+"\n"+jsonInner.get("Punti_rank")
                }
                classificaRank!!.text = "$str_classificarank "
                posizione!!.text = "$str_posizione "
                punteggio!!.text = "$str_punteggio "
            },
            Response.ErrorListener {
                classificaRank!!.text = it.toString()
                posizione!!.text = it.toString()
                punteggio!!.text = it.toString()
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


//textView!!.text = "That didn't work!"










/*import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class volley_test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_test)
    }
}*/
