package com.example.p2pcs_app

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
import org.json.JSONArray
import org.json.JSONObject

class ExploreFragment : Fragment() {
    private var company1: TextView? = null
    private var offer1: TextView? = null
    private var company2: TextView? = null
    private var offer2: TextView? = null
    private var company3: TextView? = null
    private var offer3: TextView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_explore, null)
        company1 = view.findViewById<TextView>(R.id.CompanyName)
        offer1 = view.findViewById<TextView>(R.id.OfferTitle)
        



        //val context=requireContext()
        //getOffer(context)

        return view
    }
/*fun getOffer(context: Context){
    val queue = Volley.newRequestQueue(context)
    val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/esplora.php"

    // Request a string response from the provided URL.
    //object property needed to override getparams
    val stringReq = object: StringRequest(
        Request.Method.POST, url,
        Response.Listener<String> { response ->

            val strResp = response.toString()
            val jsonArray: JSONArray = JSONArray(strResp)
            //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
            var str_company1: String = ""
            var str_offer1: String = ""
            var str_company2: String = ""
            var str_offer2: String = ""
            var str_company3: String = ""
            var str_offer3: String = ""

            for (i in 0 until jsonArray.length()) {
                val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                if(i==0) {
                    str_company1= ""+jsonInner.get("Nome_azienda")
                    str_offer1=""+jsonInner.get("Titolo_offerta")
                }
                if(i==1) {
                    str_company2=""+jsonInner.get("Nome_azienda")
                    str_offer2=""+jsonInner.get("Titolo_offerta")
                }
                if(i==2) {
                    str_company3=""+jsonInner.get("Nome_azienda")
                    str_offer3=""+jsonInner.get("Titolo_offerta")
                }



            }
            company1!!.text = "$str_company1 "
            offer1!!.text = "$str_offer1 "
            company2!!.text = "$str_company2 "
            offer2!!.text = "$str_offer2 "
            company3!!.text = "$str_company3 "
            offer3!!.text = "$str_offer3 "

        },
        Response.ErrorListener {
            //TO DO
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
}*/


}