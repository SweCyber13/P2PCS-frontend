package com.example.p2pcs_app.SearchResult.SearchResultList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import com.example.p2pcs_app.SearchResult.ActivitySearchresult
import org.json.JSONArray
import org.json.JSONObject

class FragmentSearchResultList() : Fragment() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>

    var latpl=""
    var lonpl=""
    var latal=""
    var lonal=""
    var dataarrl=""
    var minstartl=""
    var minendl=""



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search_result_list, null)

        val activity= requireContext() as ActivitySearchresult

        latpl=activity.latp
        lonpl=activity.lonp
        latal=activity.lata
        lonal=activity.lona
        dataarrl=activity.dataarr
        minstartl=activity.minstart
        minendl=activity.minend

        recyclerView= view.findViewById<RecyclerView> (R.id.recycler_view)
        data_list=  ArrayList<MyData>()
        load_search()

        return view
    }

    fun loadrecycler(data_list:  ArrayList<MyData>){

        linearLayoutManager= LinearLayoutManager(requireContext())

        customAdapter= CustomAdapter(data_list)


        recyclerView!!.apply {
            setHasFixedSize(false)

            layoutManager=linearLayoutManager
            adapter=customAdapter

        }
    }

    fun load_search() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(requireContext())
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/search.php?STARTLAT="+latpl+"&STARTLON="+lonpl+"&ENDLAT="+latal+"&ENDLON="+lonal+"&DAY="+dataarrl+"&STARTTIME="+minstartl+"&ENDTIME="+minendl

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("macchine")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    val str_modello = "" + jsonInner.get("Modello")
                    val str_marca = "" + jsonInner.get("Marca")
                    val str_proprietario="" + jsonInner.get("Proprietario")
                    var str_targa="" + jsonInner.get("Targa")
                    val str_costo="" + jsonInner.get("Costo_viaggio")
                    val str_indirizzo="" + jsonInner.get("Indirizzo")
                    //creo mydata
                    var myDatacar= MyData(str_proprietario, str_modello, str_marca, str_costo, str_indirizzo,str_targa)
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

    /*fun getsearchparameters(latp: String, lonp: String, lata: String, lona: String, dataarr: String, minstart: String, minend: String){
        latpl=latp
        lonpl=lonp
        latal=lata
        lonal=lona
        dataarrl=dataarr
        minstartl=minstart
        minendl=minend
    }*/


}