package com.example.p2pcs_app.Profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.Coupons.ActivityCoupon
import com.example.p2pcs_app.Explore.ActivityExplore
import com.example.p2pcs_app.Mission.ActivityMission
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.fragment_prize_section.*

import org.json.JSONArray
import org.json.JSONObject

class ActivityPrize : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_prize_section)
        getPoints()
missionButton.setOnClickListener{
    val intent= Intent(this, ActivityMission::class.java)
    startActivity(intent)
}
        couponButton.setOnClickListener{
            val intent= Intent(this, ActivityCoupon::class.java)
            startActivity(intent)
        }
        eventButton.setOnClickListener{
            val intent= Intent(this, ActivityExplore::class.java)
            startActivity(intent)
        }

    }

    fun getPoints(){

        //val queue = Volley.newRequestQueue(this)
        // Instantiate the RequestQueue.

        val prefs= this.getSharedPreferences(R.string.shared_preferences.toString(),0)
        val str_username=prefs.getString("username","")
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/users/read.php?USERNAME="+str_username

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("utente")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)

                    val str_buoni = "" + jsonInner.get("Punti_buoni")
                    val str_rank = "" + jsonInner.get("Punti_rank")
                    puntiBonusValue.text=str_buoni
                    puntiRankValue.text=str_rank

                }

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)




    }
}

