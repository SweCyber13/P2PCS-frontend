package com.example.p2pcs_app.CarBookings.CarRequests

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_car_requests_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var nome=view.userApplicantName
        var cognome=view.userApplicantSurname
        var modello= view.carMarca
        var targa= view.carModello
        var data=view.Day
        var inizio=view.requestStartHour
        var fine=view.requestEndHour
        var prezzo=view.prezzo
        var accetta=view.bottone
        var rifiuta=view.bottoneR


    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_car_requests_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.nome.text="Elena"
        p0.cognome.text="Pontecchiani"
        p0.modello.text="Fiat Punto"
        p0.targa.text="PD000PD"
        p0.data.text="2019-08-19"
        p0.inizio.text="12.00"
        p0.fine.text="20.00"
        p0.prezzo.text="40.00€"
        p0.accetta.setOnClickListener{
            p0.accetta.text="Accettato"
            p0.accetta.isEnabled=false
            p0.rifiuta.visibility=View.INVISIBLE
        }
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



