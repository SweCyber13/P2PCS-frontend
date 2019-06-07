package com.example.p2pcs_app.Model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.ApiRequestHandler
import com.example.p2pcs_app.ModelCallback
import com.example.p2pcs_app.VolleyCallback
import org.json.JSONArray
import org.json.JSONObject

class Model {

    companion object {

        fun getUser(context: Context,username:String, callback: ModelCallback<User>) {
            var jsonstring:String=""
            val url="users/read.php?USERNAME=$username" //$concatena le stringhe
            try {
                ApiRequestHandler.makerequest(url,context,object : VolleyCallback {
                    override fun onSuccess(result:String) {
                        jsonstring=result
                        //ho ottenuto la risposta ora devo parsare il json
                        val jsonObj: JSONObject = JSONObject(jsonstring)
                        val jsonArray: JSONArray = jsonObj.getJSONArray("utente")
                        //devo mettere un nome in qualche modo all'array su php
                        var user: User=User()
                        for (i in 0 until jsonArray.length()) {
                            val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                            user.username=jsonInner.getString("Username")
                            user.nome=jsonInner.getString("Nome")
                            user.cognome=jsonInner.getString("Cognome")
                            user.mail=jsonInner.getString("Mail")
                            user.eta=jsonInner.getInt("Eta")
                            //DA CONTINUARE....

                        }
                        Log.i("ciao","model success")
                        callback.onSuccess(user)

                    }
                })
            } catch (error: VolleyError) {
                throw error
            }

        }





























    }
}