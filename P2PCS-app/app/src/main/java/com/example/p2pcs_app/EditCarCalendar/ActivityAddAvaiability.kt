package com.example.p2pcs_app.EditCarCalendar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_add_avaiability.*
import java.util.*

class ActivityAddAvaiability: AppCompatActivity() {
    var minstart=""
    var minend=""
    var dataarr=""
    var targapassed=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_avaiability)

        targapassed=intent.getStringExtra("TARGA")

        //seleziona data
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        model.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                    model.setText("" + mDay + "-" + (mMonth+1) + "-" + mYear)
                    //salvo data
                    var mon=""
                    if(mMonth+1<10){
                        mon="0"+(mMonth+1)
                    }
                    else
                    { mon=""+(mMonth+1)}
                    dataarr="" + mYear + "-" + mon + "-" + mDay
                }, year, month, day )

            dpd.show()
        }


        //seleziona ora partenza
        marca.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay:Int, minute:Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                marca.setText(""+hourOfDay+":"+minute)
                //salvo minuti
                minstart=""+(hourOfDay*60+minute)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }



        //seleziona ora arrivo
        InTarga.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay:Int, minute:Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                InTarga.setText(""+hourOfDay+":"+minute)
                //salvo ora
                minend=""+(hourOfDay*60+minute)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //listener bottone
        reserve.setOnClickListener {
            save_avaiability()
        }
    }

    fun save_avaiability(){
        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/car/avaiability/update.php?TARGA="+targapassed+"&GIORNO="+dataarr+"&START="+minstart+"&END="+minend
        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                //Toast.makeText(this, "Aggiornata con successo!" , Toast.LENGTH_LONG).show()
                val toast = Toast.makeText(applicationContext, "Disponibilità aggiunta con successo!", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                val toastContentView = toast.view as LinearLayout
                val imageView = ImageView(applicationContext)
                imageView.setImageResource(R.drawable.aa)
                toastContentView.addView(imageView, 0)
                toast.show()
            },
            Response.ErrorListener {
                //throw(DatabaseException("errore"))
            })

        queue.add(stringReq)


    }
}