package com.example.p2pcs_app.EditCarCalendar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_edit_car_calendar.*
import org.json.JSONArray
import org.json.JSONObject

//chiamerà activity_edit_car_calendar
class ActivityEditCarCalendar : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>
    var targapassed=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car_calendar)

        targapassed=intent.getStringExtra("TARGA")
        recyclerView= findViewById<RecyclerView> (R.id.calendar_recycler_view)
        data_list=  ArrayList<MyData>()
        //implementare recyclerview con le disponibilità
        //pulsante + per aggiungere nuova disponibilità che fa aprire un dialog devo mostrare data minuto inizio e fine
        //aggiunta della nuova disponibilità

        //LISTENER PER IL BOTTONE
        new_car.setOnClickListener{
            val intent= Intent(this, ActivityAddAvaiability::class.java)
            intent.putExtra("TARGA", targapassed)
            startActivity(intent)
        }
        load_avaiability()
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

    fun load_avaiability(){

        //http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/avaiability/read.php?TARGA=AB455CD&GIORNO=2019-07-16

        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/avaiability/read.php?TARGA="+targapassed+"&GIORNO=2019-08-19"

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("disponibilita")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    var str_inizio = "" + jsonInner.get("inizio")
                    var str_fine = "" + jsonInner.get("fine")


                    //passo da minuti a ore
                    val inizio_ore: Int=(str_inizio.toInt()/60)
                    val inizio_minuti: Int = (str_inizio.toInt()%60)
                    str_inizio=""+inizio_ore+":"+inizio_minuti
                    val fine_ore: Int=(str_fine.toInt()/60)
                    val fine_minuti: Int = (str_fine.toInt()%60)
                    str_fine=""+fine_ore+":"+fine_minuti

                    //creo mydata
                    var myDatacar= MyData("2019-08-19", str_inizio, str_fine)
                    data_list.add(myDatacar)
                }
                //ho aggiunto tutte le disponibilità a datalist chiamo la recyclerview
                loadrecycler(data_list)

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }
}