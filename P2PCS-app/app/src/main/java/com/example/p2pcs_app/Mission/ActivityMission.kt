package com.example.p2pcs_app.Mission

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import org.json.JSONArray
import org.json.JSONObject

class ActivityMission : Activity() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mission)
        recyclerView= findViewById<RecyclerView> (R.id.recycler_view)
        data_list=  ArrayList<MyData>()
        loadrecycler(data_list)
        getMission()
    }




    fun loadrecycler(data_list:  ArrayList<MyData>){

        linearLayoutManager= LinearLayoutManager(this)

        customAdapter= CustomAdapter(data_list)


        recyclerView?.apply {
            setHasFixedSize(false)

            layoutManager=linearLayoutManager
            adapter=customAdapter
            Log.e("fragment mission", data_list.size.toString())

        }
    }



    // function for network call
    fun getMission() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/mission/readall.php"

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("mission")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    val str_titolo = ""  + jsonInner.get("Titolo")
                    val str_descr = "" + jsonInner.get("Descrizione")
                    val str_punti_rank=""+jsonInner.get("Premio_rank")
                    val str_punti_buoni=""+jsonInner.get("Premio_buono")

                    //creo mydata
                    var myDataMission = MyData(str_titolo, str_descr, str_punti_rank, str_punti_buoni)
                    data_list.add(myDataMission)
                }
                Log.e("fragment mission", data_list.size.toString())

                customAdapter!!.notifyDataSetChanged()
            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }
}


