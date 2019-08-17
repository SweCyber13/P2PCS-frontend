package com.example.p2pcs_app.CarNew


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.Cars.ActivityCars
import com.example.p2pcs_app.R
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.activity_insert_car.*
import java.util.*
import android.widget.LinearLayout
import android.view.Gravity
import android.widget.ImageView


class ActivityNewCar : AppCompatActivity() {

    var latp=""
    var lonp=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_car)

        new_car_anno.setOnClickListener{

        }

        Places.initialize(this, resources.getString(R.string.google_maps_key))
        //val startCity =findViewById<AutoCompleteTextView>(R.id.startCity)
        //val endCity=findViewById<AutoCompleteTextView>(R.id.endCity)
        val startCity = supportFragmentManager.findFragmentById(R.id.new_car_indirizzo) as AutocompleteSupportFragment
        startCity.setHint("posizione")
        startCity.setPlaceFields(
            Arrays.asList(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS
            )
        )

        //getcontext and set listeners
        val context: Context = this

        startCity.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Toast.makeText(context, "errore", Toast.LENGTH_LONG).show()
            }

            override fun onPlaceSelected(place: Place) {
                Toast.makeText(context, place.address, Toast.LENGTH_LONG).show()
                //salvare dati
                latp=""+place.latLng!!.latitude
                lonp=""+place.latLng!!.longitude

            }
        })

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
        val anno=new_car_anno.text
        val cavalli=new_car_cavalli.text
        val cilindrata=new_car_cilindrata.text
        val raggio=new_car_raggio_max.text
        val chilometraggio=new_car_chilometraggio.text
        val tariffa=new_car_tariffa.text
        val proprietario= str_username
        val lat=latp
        val lon=lonp
        val indirizzo="Torre Archimede Padova"

        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/create.php?TARGA="+targa+"&PROPRIETARIO="+proprietario+"&MARCA="+marca+"&ANNOPRODUZIONE="+anno+"&CAVALLI="+cavalli+"&CILINDRATA="+cilindrata+"&RAGGIO="+raggio+"&CHILOMETRAGGIO="+chilometraggio+"&MODELLO="+modello+"&TARIFFAORARIA="+tariffa+"&LATITUDINE="+lat+"&LONGITUDINE="+lon+"&INDIRIZZO="+indirizzo

        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                //Toast.makeText(this, "l'auto è stata aggiunta con successo" , Toast.LENGTH_LONG).show()
                val toast = Toast.makeText(applicationContext, "Auto aggiunta con successo!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                val toastContentView = toast.view as LinearLayout
                val imageView = ImageView(applicationContext)
                imageView.setImageResource(R.drawable.aa)
                toastContentView.addView(imageView, 0)
                toast.show()

            },
            Response.ErrorListener {
                Toast.makeText(this, "errore nella creazione dell'auto" , Toast.LENGTH_LONG).show()
            })

        queue.add(stringReq)

    }
}
