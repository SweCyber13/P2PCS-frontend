package com.example.p2pcs_app.LeaderBoard.GlobalLeaderboard

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.fragment_global_leaderboard.*
import org.json.JSONArray
import org.json.JSONObject

class GlobalFragmentLeaderboard : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_global_leaderboard, null)
        getBoard(requireContext())
        return view
    }






    // function for network call
    fun getBoard(context: Context) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/classifica.php"

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = object: StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->

                val strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray() //devo mettere un nome in qualche modo all'array su php
                var str_username: String = ""
                var str_posizione: String = ""
                var str_punteggio: String = ""
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    str_posizione=str_posizione+"\n"+ (i+1)
                    str_username = str_username +"\n" + jsonInner.get("Username")
                    str_punteggio=str_punteggio+"\n"+jsonInner.get("Punti_rank")
                }
                username!!.text = "$str_username "
                position!!.text = "$str_posizione "
                rank!!.text = "$str_punteggio "
            },
            Response.ErrorListener {
                username!!.text = it.toString()
                position!!.text = it.toString()
                rank!!.text = it.toString()
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
