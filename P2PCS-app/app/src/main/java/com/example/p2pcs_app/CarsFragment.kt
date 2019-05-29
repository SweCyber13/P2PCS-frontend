package com.example.p2pcs_app

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class CarsFragment : Fragment() {
    private var modello: TextView? = null
    private var marca: TextView? = null
    private var targa: TextView? = null
    private var anno: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_cars, null)
        modello = view.findViewById<TextView>(R.id.textModel)
        marca = view.findViewById<TextView>(R.id.textMarca)
        anno = view.findViewById<TextView>(R.id.year)
        targa = view.findViewById<TextView>(R.id.textTarga)
        val button = view.findViewById<Button>(R.id.otherInfo)
        button.setOnClickListener{
            val intent= Intent(requireContext(), VisualizzaMacchinaActivity::class.java)
            startActivity(intent)
        }

        val context=requireContext()
        getCars(context)

        return view
    }
   fun getCars(context: Context){
        val queue = Volley.newRequestQueue(context)
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
                var str_anno: String = ""
                var str_targa: String = ""


                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_modello =  "" + jsonInner.get("Modello")
                    str_marca = "" + jsonInner.get("Marca")
                    str_anno = "" + jsonInner.get("Anno_produzione")
                    str_targa = "" + jsonInner.get("Targa")

                }
                modello!!.text = "$str_modello "
                marca!!.text = "$str_marca "
                anno!!.text = "$str_anno "
                targa!!.text = "$str_targa "

            },
            Response.ErrorListener {
                modello!!.text = it.toString()
                marca!!.text = it.toString()
                anno!!.text = it.toString()
                targa!!.text = it.toString()


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
