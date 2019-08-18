package com.example.p2pcs_app.Search

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import android.content.Context
import android.content.Intent
import android.widget.*
import com.example.p2pcs_app.SearchResult.ActivitySearchresult
import com.google.android.gms.common.api.Status
import kotlinx.android.synthetic.main.activity_other_user.*
import kotlinx.android.synthetic.main.activity_search.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.internal.i
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.activity_car_requests_card.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.text.SimpleDateFormat
import java.util.*


class ActivitySearch : AppCompatActivity() {

    var latp=""
    var lonp=""
    var lata=""
    var lona=""
    var dataarr=""
    var minstart=""
    var minend=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search)

        //mok prenotazione

        val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        if(str_username=="elena"){
            //sono sulla ricerca con il profilo che deve cercare
            prefs.edit().putString("mostrarichiesta","si").apply()
            prefs.edit().putString("mostraprenotazione","1").apply()
        }


        val button = findViewById<Button>(R.id.search_search_car)
        button.setOnClickListener {
            val intent = Intent(this, ActivitySearchresult::class.java)
            intent.putExtra("LATP", latp)
            intent.putExtra("LONP", lonp)
            intent.putExtra("LATA", lata)
            intent.putExtra("LONA", lona)
            intent.putExtra("DAY", dataarr)
            intent.putExtra("MINP", minstart)
            intent.putExtra("MINA", minend)
            startActivity(intent)
        }

        // Initialize Places.
        Places.initialize(this, resources.getString(R.string.google_maps_key))
        //val startCity =findViewById<AutoCompleteTextView>(R.id.startCity)
        //val endCity=findViewById<AutoCompleteTextView>(R.id.endCity)
        val startCity = supportFragmentManager.findFragmentById(R.id.startCity) as AutocompleteSupportFragment
        startCity.setHint("città di partenza")
        val endCity = supportFragmentManager.findFragmentById(R.id.endCity) as AutocompleteSupportFragment
        endCity.setHint("città di arrivo")
        //setto che cosa prendere dai places per partenza e arrivo
        startCity.setPlaceFields(
            Arrays.asList(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS
            )
        )
        endCity.setPlaceFields(
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

        endCity.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Toast.makeText(context, "errore", Toast.LENGTH_LONG).show()
            }

            override fun onPlaceSelected(place: Place) {
                Toast.makeText(context, place.address, Toast.LENGTH_LONG).show()
                //salvare dati
                lata=""+place.latLng!!.latitude
                lona=""+place.latLng!!.longitude
            }
        })

        //seleziona data
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        startDate.setOnClickListener {
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                startDate.setText("" + mDay + "/" + (mMonth+1) + "/" + mYear)
                //salvo data
                dataarr="" + mYear + "-" + "08" + "-" + mDay
            }, year, month, day )

        dpd.show()
    }


        //seleziona ora partenza
        startTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay:Int, minute:Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                startTime.setText(""+hourOfDay+":"+minute)
                //salvo minuti
                minstart=""+(hourOfDay*60+minute)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }



        //seleziona ora arrivo
        endTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay:Int, minute:Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                endTime.setText(""+hourOfDay+":"+minute)
                //salvo ora
                minend=""+(hourOfDay*60+minute)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }




   }

    private fun showDialog(title:String, message:String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.ok, null)
            .create()
            .show()

    }

}
