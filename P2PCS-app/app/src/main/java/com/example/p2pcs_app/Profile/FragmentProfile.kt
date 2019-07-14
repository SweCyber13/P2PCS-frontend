package com.example.p2pcs_app.Profile

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.p2pcs_app.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONArray
import org.json.JSONObject

class FragmentProfile : Fragment(), FragmentProfileContract.View {
    //lateinit  var mypresenter: FragmentProfileContract.Presenter
    //var listener: RequestQueueListener?=null
    override fun displayUserinfo(Username:String) {
        //setto tutte le textview
        if(view!=null){
            val usernameview = view!!.findViewById<TextView>(R.id.Username)
            usernameview.setText(Username)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, null)

        //mypresenter=FragmentProfilePresenter(this,listener?.getRequestQueue()!!)
        //mypresenter=FragmentProfilePresenter(this,requireContext())

        //mypresenter.getUserinfo()
        getuser()

        return view
    }

    fun getuser(){

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
                    val str_nome = "" + jsonInner.get("Nome")
                    val str_cognome = "" + jsonInner.get("Cognome")
                    val str_eta = "" + jsonInner.get("Eta")
                    val str_sesso = "" + jsonInner.get("Sesso")
                    val str_citta = "" + jsonInner.get("Citta")
                    val str_mail = "" + jsonInner.get("Mail")
                    val str_npatente = "" + jsonInner.get("Numero_patente")
                    val str_occupazione = "" + jsonInner.get("Occupazione")
                    val str_datapatente = "" + jsonInner.get("Data_rilascio_p")
                    val str_buoni = "" + jsonInner.get("Punti_buoni")
                    val str_rank = "" + jsonInner.get("Punti_rank")
                    Name.text=str_nome
                    Surname.text=str_cognome
                    Username.text=str_username
                    Email.text=str_mail
                    Age.text=str_eta
                    City.text=str_citta
                    DateDrivingLicense.text=str_datapatente
                    DrivingLicense.text=str_npatente
                    Job.text=str_occupazione
                    if(str_sesso=="F"){
                        Sex.text="Donna"
                    }
                    else
                        Sex.text="Uomo"
                }

            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)




    }
}
