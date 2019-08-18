package com.example.p2pcs_app.Explore

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import org.json.JSONArray
import org.json.JSONObject

class ActivityExplore : Activity() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_explore)
        recyclerView= findViewById<RecyclerView> (R.id.recycler_view)
        data_list=  ArrayList<MyData>()
        loadrecycler(data_list)
        getExplore()
    }




    fun loadrecycler(data_list:  ArrayList<MyData>){

        linearLayoutManager= LinearLayoutManager(this)

        customAdapter= CustomAdapter(data_list, this)


        recyclerView?.apply {
            setHasFixedSize(false)

            layoutManager=linearLayoutManager
            adapter=customAdapter
            Log.e("fragment explore", data_list.size.toString())

        }
    }



    // function for network call
    fun getExplore() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/event/readall.php"

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("eventi")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    val str_nome = ""  + jsonInner.get("Nome_azienda")
                    val str_titolo = "" + jsonInner.get("Titolo_offerta")

                    //creo mydata
                    var myDataEvent = MyData(str_nome, str_titolo)
                    data_list.add(myDataEvent)
                }
                Log.e("fragment explore", data_list.size.toString())

                customAdapter!!.notifyDataSetChanged()
            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }
}


