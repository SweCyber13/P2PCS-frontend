package com.example.p2pcs_app

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONArray
import org.json.JSONObject

class ApiRequestHandler{
    companion object {
        val domain= "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/"
        var strResp=""
        fun makerequest(url:String, requestQueue: RequestQueue, callback: VolleyCallback){
            //lancia la richiesta e attende il risultato
            val finalurl=domain+url
            val stringReq = StringRequest(Request.Method.GET, finalurl,
                Response.Listener<String> {
                        response ->
                        Log.i("ciao","success volley")
                        strResp = response.toString()
                        callback.onSuccess(strResp) //callback da definire alla chiamata di makerequest
                },
                Response.ErrorListener {
                    Log.i("ciao","I am here error")
                    //Log.i("ciao", it.networkResponse.statusCode.toString())
                    //throw it //lascia l'handling dell'errore al chiamante
                })

            Log.i("ciao",finalurl)
            requestQueue.add(stringReq) //lancia la richiesta
        }
    }
}