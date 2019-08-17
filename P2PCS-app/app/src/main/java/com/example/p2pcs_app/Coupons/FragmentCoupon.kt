package com.example.p2pcs_app.Coupons

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.EditCar.ActivityEditCar
import com.example.p2pcs_app.R
import org.json.JSONArray
import org.json.JSONObject

class FragmentCoupon : Fragment() {
    private var azienda: TextView? = null
    private var titolo: TextView? = null
    private var descrizione: TextView? = null
    private var scadenza: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_coupon_card, null) //view=card
        //val view = inflater.inflate(R.layout.fragment_cars, null)

        //val View= view.findViewById<LinearLayout>(R.id.car_card_list) //dove salvo il linear layout a cui vado ad aggiungere card
        //da fare




        azienda = view.findViewById<TextView>(R.id.nome)
        titolo = view.findViewById<TextView>(R.id.titolo)
        //tariffa = view.findViewById<TextView>(R.id.tariffaTx)
        //targa = view.findViewById<TextView>(R.id.textTarga)
        val button = view.findViewById<Button>(R.id.button_info)
        button.setOnClickListener{
            val intent= Intent(requireContext(), ActivityEditCar::class.java) //mettere nuova activity coupon details
            startActivity(intent)
        }




        val context=requireContext()
        getCoupon(context)

        return view
    }
    fun getCoupon(context: Context){
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/visualizzacoupon.php"

        val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_azienda: String = ""
                var str_titolo: String = ""
                var str_descrizione: String = ""
                var str_scadenza: String = ""


                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_azienda =  "" + jsonInner.get("Nome_azienda")
                    str_titolo = "" + jsonInner.get("Titolo_offerta")
                    str_descrizione = "" + jsonInner.get("Descrizione")
                    str_scadenza = "" + jsonInner.get("Scadenza")

                }
                azienda!!.text = "$str_azienda "
                titolo!!.text = "$str_titolo "
                descrizione!!.text = "$str_descrizione "
                scadenza!!.text = "$str_scadenza "

            },
            Response.ErrorListener {
                azienda!!.text = it.toString()
                titolo!!.text = it.toString()
                descrizione!!.text = it.toString()
                scadenza!!.text = it.toString()


            })
        //need to override getparams to get the post request
        {
            override fun getParams() : Map<String,String> {
                val params = HashMap<String, String>()
                params.put("COUPON",prefs.getString("coupon","vuoto"))
                return params
            }
        }

        queue.add(stringReq)
    }


}