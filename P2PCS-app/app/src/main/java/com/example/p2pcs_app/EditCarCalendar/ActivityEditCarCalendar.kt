package com.example.p2pcs_app.EditCarCalendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R

//chiamerà activity_edit_car_calendar
class ActivityEditCarCalenar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car_calendar)

        //implementare recyclerview con le disponibilità
        //pulsante + per aggiungere nuova disponibilità che fa aprire un dialog devo mostrare data minuto inizio e fine
        //aggiunta della nuova disponibilità
    }
}