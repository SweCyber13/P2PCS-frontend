package com.example.p2pcs_app.Explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.Explore.MyData
import com.example.p2pcs_app.R

class FragmentExplore : Fragment() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_explore, null)


        val recyclerView= view.findViewById<RecyclerView> (R.id.recycler_view)
        val data_list=  ArrayList<MyData>()
        load_data(data_list)

        linearLayoutManager= LinearLayoutManager(requireContext())

        customAdapter= CustomAdapter(data_list)


       recyclerView.apply {
           setHasFixedSize(true)

           layoutManager=linearLayoutManager
           adapter=customAdapter

       }

        return view
    }

    fun load_data (data_list: ArrayList<MyData>){ //prova con 3 card

        var myData1= MyData(1, "lol", "lol")


        var myData2= MyData(2, "lol", "lol")


        var myData3= MyData(3, "lol", "lol")


        data_list.add(myData1)
        data_list.add(myData2)
        data_list.add(myData3)


    }
/*fun getOffer(context: Context){
    val queue = Volley.newRequestQueue(context)
    val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/esplora.php"

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

            for (i in 0 until jsonArray.length()) {
                val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                if(i==0) {
                    str_company1= ""+jsonInner.get("Nome_azienda")
                    str_offer1=""+jsonInner.get("Titolo_offerta")
                }
                if(i==1) {
                    str_company2=""+jsonInner.get("Nome_azienda")
                    str_offer2=""+jsonInner.get("Titolo_offerta")
                }
                if(i==2) {
                    str_company3=""+jsonInner.get("Nome_azienda")
                    str_offer3=""+jsonInner.get("Titolo_offerta")
                }



            }
            company1!!.text = "$str_company1 "
            offer1!!.text = "$str_offer1 "
            company2!!.text = "$str_company2 "
            offer2!!.text = "$str_offer2 "
            company3!!.text = "$str_company3 "
            offer3!!.text = "$str_offer3 "

        },
        Response.ErrorListener {
            //TO DO
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
}*/


}