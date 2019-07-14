package com.example.p2pcs_app.EditCar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.EditCarCalendar.ActivityEditCarCalenar
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_edit_car.*
import org.json.JSONArray
import org.json.JSONObject




class ActivityEditCar : AppCompatActivity() {
    //da visualizza macchina activity

    private var targapassed=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car)

        val intentpassed = intent
        targapassed=intent.getStringExtra("MACCHINA")
        //ho la targa
        //Modello.text=targapassed



        //ottenere i parametri passati dall'intent
        //settare tutti i campi di testo
        //possibilmente mettere i listener per la modifica
        //possibilmente mettere listener per l'eliminazione e tornare indietro
        //mettere listener per calendario disponibilità

        //listener calendario disponibilità
        Calendario.setOnClickListener{
            val intent= Intent(this, ActivityEditCarCalenar::class.java)
            startActivity(intent)
        }

        getcar()


    }

    override fun onRestart() {
        super.onRestart()
        getcar()
    }

    fun getcar() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/read.php?TARGA="+targapassed

        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray= jsonObj.getJSONArray("macchina")
                for (i in 0 until jsonArray.length()) {
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    Marca.text = "" + jsonInner.get("Marca")
                    Modello.text = "" + jsonInner.get("Modello")
                    AnnoProduzione.text = "" + jsonInner.get("Anno_produzione")
                    cavalliValue.text = "" + jsonInner.get("Cavalli")
                    cilindrataValue.text= "" + jsonInner.get("Cilindrata")
                    raggioValue.text = "" + jsonInner.get("Raggio_percorrenza")
                    chilometraggioValue.text = "" + jsonInner.get("Kilometraggio")
                    tariffaValue.text = "" + jsonInner.get("Tariffa_oraria")
                    indirizzoValue.text= ""+ jsonInner.get("Indirizzo")
                    // = "" + jsonInner.get("Indirizzo")
                }


            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)

    }

}