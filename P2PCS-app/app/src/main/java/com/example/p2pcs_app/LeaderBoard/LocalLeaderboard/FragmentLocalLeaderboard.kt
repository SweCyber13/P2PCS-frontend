package com.example.p2pcs_app.LeaderBoard.LocalLeaderboard

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
import com.example.p2pcs_app.LeaderBoard.ActivityLeaderboard
import com.example.p2pcs_app.LeaderBoard.LocalLeaderboard.CustomAdapter
import com.example.p2pcs_app.LeaderBoard.LocalLeaderboard.MyData
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONArray
import org.json.JSONObject

class  FragmentLocalLeaderboard : Fragment() {
var str_citta=""
    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null
    private lateinit var data_list: ArrayList<MyData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_local_leaderboard, null)

        val activity= requireContext() as ActivityLeaderboard



        recyclerView= view.findViewById<RecyclerView> (R.id.recycler_view)
        data_list=  ArrayList<MyData>()
        getBoard()

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

fun getBoard(){


        // Instantiate the RequestQueue.
        val prefs = requireContext().getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        val queue = Volley.newRequestQueue(requireContext())
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/users/read.php?USERNAME="+str_username

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray= jsonObj.getJSONArray("utente")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)

                     str_citta = "" + jsonInner.get("Citta")
                    get2()



                }

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)
    }

    fun get2(){
        val prefs = requireContext().getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        val queue = Volley.newRequestQueue(requireContext())
        // Instantiate the RequestQueue
        val url2: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/leaderboard/globalLeaderboard.php"


        val stringReq2 = StringRequest(
            Request.Method.POST, url2,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("globalleaderboard")
                var contatore=0
                for (i in 0 until jsonArray.length()) {

                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    if(jsonInner.get("Citta")==str_citta) {
                        contatore=contatore+1
                        val str_posizione = "" + (contatore)
                        val str_username = "" + jsonInner.get("Username")
                        val str_rank = "" + jsonInner.get("Punti_rank")

                        //creo mydata
                        var myDataLLeaderboard = MyData(str_posizione, str_username, str_rank)
                        data_list.add(myDataLLeaderboard)}
                }
                //ho aggiunto tutte le auto a datalist chiamo la recyclerview
                loadrecycler(data_list)

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq2)

    }
}




