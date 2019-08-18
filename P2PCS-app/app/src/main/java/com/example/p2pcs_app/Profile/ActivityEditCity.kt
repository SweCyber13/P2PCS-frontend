package com.example.p2pcs_app.Profile

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.R
import com.android.volley.Request
import com.android.volley.Response
import kotlinx.android.synthetic.main.edit_city.*


class ActivityEditCity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_city)

        update.setOnClickListener{

        }

editCity()



    }

    fun editCity(){
        val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        val citta=citta.text


        val queue = Volley.newRequestQueue(this)
        val url: String = "http://ec2-18-206-124-50.compute-1.amazonaws.com/Api/user/update.php?CITTA="+citta+"&USERNAME="+str_username
        // Request a string response from the provided URL.
        //object property needed to override getparams
        val stringReq = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->

                //risponde successo o no
                val strResp = response.toString()
                //Toast.makeText(this, "l'auto è stata aggiunta con successo" , Toast.LENGTH_LONG).show()
                val toast = Toast.makeText(applicationContext, "Citta aggiornata", Toast.LENGTH_SHORT)
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


