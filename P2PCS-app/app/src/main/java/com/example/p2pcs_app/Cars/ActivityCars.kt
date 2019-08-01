package com.example.p2pcs_app.Cars

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.CarNew.ActivityNewCar
import com.example.p2pcs_app.R

import kotlinx.android.synthetic.main.activity_cars.*
import kotlinx.android.synthetic.main.fragment_cars.*
import org.json.JSONArray
import org.json.JSONObject

class ActivityCars : AppCompatActivity() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cars)
        recyclerView= findViewById<RecyclerView> (R.id.cars_recycler_view)
        data_list=  ArrayList<MyData>()

        //listener per nuova macchina
        new_car.setOnClickListener{
            val intent= Intent(this, ActivityNewCar::class.java)
            startActivity(intent)
        }

        load_cars()

    }

    fun loadrecycler(data_list:  ArrayList<MyData>){

        linearLayoutManager= LinearLayoutManager(this)

        customAdapter= CustomAdapter(data_list,this)


        recyclerView!!.apply {
            setHasFixedSize(false)

            layoutManager=linearLayoutManager
            adapter=customAdapter

        }
    }
    fun load_cars () {
        // Instantiate the RequestQueue.
        val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/readall.php?PROPRIETARIO="+str_username

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("macchine")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    val str_targa = "" + jsonInner.get("Targa")
                    val str_modello = "" + jsonInner.get("Modello")
                    val str_marca = "" + jsonInner.get("Marca")
                    val str_anno = "" + jsonInner.get("Anno_produzione")

                    //creo mydata
                    var myDatacar= MyData(str_marca, str_modello, str_targa, str_anno)
                    data_list.add(myDatacar)
                }
                //ho aggiunto tutte le auto a datalist chiamo la recyclerview
                loadrecycler(data_list)

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }

}
