package com.example.p2pcs_app.CarNew

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_insert_car.*

class ActivityNewCar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_car)

        new_car_anno.setOnClickListener{

        }
        new_car_button.setOnClickListener{
            savecar()
            //salvo su database
            //ritorno indietro
        }
    }

    fun savecar(){
        val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        val targa=new_car_targa.text
        val marca=new_car_marca.text
        val modello=new_car_modello.text
        //val anno=new_car_anno.text
        val anno=1997
        val cavalli=new_car_cavalli.text
        val cilindrata=new_car_cilindrata.text
        val raggio=new_car_raggio_max.text
        val chilometraggio=1
        val tariffa=new_car_tariffa.text
        val proprietario= str_username
        val lat=1
        val lon=1
        val indirizzo="Padova"

        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/create.php?TARGA="+targa+"&PROPRIETARIO="+proprietario+"&MARCA="+marca+"&ANNOPRODUZIONE="+anno+"&CAVALLI="+cavalli+"&CILINDRATA="+cilindrata+"&RAGGIO="+raggio+"&CHILOMETRAGGIO="+chilometraggio+"&MODELLO="+modello+"&TARIFFAORARIA="+tariffa+"&LATITUDINE="+lat+"&LONGITUDINE="+lon+"&INDIRIZZO="+indirizzo

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                Toast.makeText(this, "successo l'auto è stata aggiunta" , Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener {
                Toast.makeText(this, "errore nella crazione dell'auto" , Toast.LENGTH_LONG).show()
            })

        queue.add(stringReq)

    }
}
