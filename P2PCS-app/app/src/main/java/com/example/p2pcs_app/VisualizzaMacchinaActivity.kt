package com.example.p2pcs_app

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class VisualizzaMacchinaActivity : AppCompatActivity() {
    private var modello: TextView? = null
    private var marca: TextView? = null
    private var targa: TextView? = null
    private var anno: TextView? = null
    private var cavalli: TextView? = null
    private var cilindrata: TextView? = null
    private var raggio: TextView? = null
    private var chilometraggio: TextView? = null
    private var tariffa: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cars)
        marca = findViewById<TextView>(R.id.Marca)
        modello = findViewById<TextView>(R.id.Modello)
        targa = findViewById<TextView>(R.id.Targa)
        anno = findViewById<TextView>(R.id.AnnoProduzione)
        cavalli = findViewById<TextView>(R.id.cavalliValue)
        cilindrata = findViewById<TextView>(R.id.cilindrataValue)
        raggio = findViewById<TextView>(R.id.raggioValue)
        chilometraggio = findViewById<TextView>(R.id.chilometraggioValue)
        tariffa = findViewById<TextView>(R.id.tariffaValue)

        getCar(this)
    }

    // function for network call
    fun getCar(context: Context) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/visualizzamacchina.php"

        val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_modello: String = ""
                var str_marca: String = ""
                var str_targa: String = ""
                var str_anno: String = ""
                var str_cavalli: String = ""
                var str_cilindrata: String = ""
                var str_raggio: String = ""
                var str_chilometraggio: String = ""
                var str_tariffa: String = ""

                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_modello = ""+ jsonInner.get("Modello")
                    str_marca = ""+ jsonInner.get("Marca")
                    str_targa = "" + jsonInner.get("Targa")
                    str_anno = "" + jsonInner.get("Anno_produzione")
                    str_cavalli = "" + jsonInner.get("Cavalli")
                    str_cilindrata = "" + jsonInner.get("Cilindrata")
                    str_raggio = "" + jsonInner.get("Raggio_percorrenza")
                    str_chilometraggio = "" + jsonInner.get("Kilometraggio")
                    str_tariffa = "" + jsonInner.get("Tariffa_oraria")
                }
                modello!!.text = "$str_modello "
                marca!!.text = "$str_marca "
                targa!!.text = "$str_targa "
                anno!!.text = "$str_anno "
                cavalli!!.text = "$str_cavalli "
                cilindrata!!.text = "$str_cilindrata "
                raggio!!.text = "$str_raggio "
                chilometraggio!!.text = "$str_chilometraggio "
                tariffa!!.text = "$str_tariffa "
            },
            Response.ErrorListener {
                modello!!.text = it.toString()
                marca!!.text = it.toString()
                targa!!.text = it.toString()
                anno!!.text = it.toString()
                cavalli!!.text = it.toString()
                cilindrata!!.text = it.toString()
                raggio!!.text = it.toString()
                chilometraggio!!.text = it.toString()
                tariffa!!.text = it.toString()


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


