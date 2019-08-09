package com.example.p2pcs_app.Coupons

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
import org.json.JSONArray
import org.json.JSONObject

class FragmentCoupon : Fragment() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_coupon, null)

        //val activity= requireContext() as ActivityLeaderboard



        recyclerView= view.findViewById<RecyclerView> (R.id.recycler_view)
        data_list=  ArrayList<MyData>()
        getCoupon()

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



    // function for network call
    fun getCoupon() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/coupon/readall.php"

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("coupons")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    val str_nome = ""  + jsonInner.get("Nome_azienda")
                    val str_titolo = "" + jsonInner.get("Titolo")
                    val str_punti = ""+ jsonInner.get("Soglia_punti")

                    //creo mydata
                    var myDataCoupon = MyData(str_nome, str_titolo, str_punti)
                    data_list.add(myDataCoupon)
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


