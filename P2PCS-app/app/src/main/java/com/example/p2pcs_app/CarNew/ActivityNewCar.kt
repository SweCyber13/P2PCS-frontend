package com.example.p2pcs_app.CarNew

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_insert_car.*

class ActivityNewCar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_car)

        new_car_button.setOnClickListener{
            //prendo i parametri dagli edittext
            //salvo su database
            //ritorno indietro
        }
    }
}
