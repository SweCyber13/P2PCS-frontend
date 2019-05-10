package com.example.p2pcs_app

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ExploreFragment : Fragment() {
    private var company1: TextView? = null
    private var offer1: TextView? = null
    private var company2: TextView? = null
    private var offer2: TextView? = null
    private var company3: TextView? = null
    private var offer3: TextView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_explore, null)
        company1 = view.findViewById<TextView>(R.id.Modello)
        offer1 = view.findViewById<TextView>(R.id.Marca)
        company2 = view.findViewById<TextView>(R.id.Company2)
        offer2 = view.findViewById<TextView>(R.id.TitleOffer2)
        company3 = view.findViewById<TextView>(R.id.Company3)
        offer3 = view.findViewById<TextView>(R.id.TitleOffer3)
        val button = view.findViewById<Button>(R.id.InfoOffer)
        

        //val context=requireContext()
        //getOffer(context)

        return view
    }/*
fun getOffer(context: Context){
    val queue = Volley.newRequestQueue(context)
    val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/offerte.php"

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


//da continuare
            for (i in 0 until jsonArray.length()) {
                val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                str_modello =  "\n" + jsonInner.get("Modello")
                str_marca = "\n" + jsonInner.get("Marca")


            }
            modello!!.text = "$str_modello "
            marca!!.text = "$str_marca "
            //targa!!.text = "$str_targa "

        },
        Response.ErrorListener {
            modello!!.text = it.toString()
            marca!!.text = it.toString()
            //targa!!.text = it.toString()


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
*/

}