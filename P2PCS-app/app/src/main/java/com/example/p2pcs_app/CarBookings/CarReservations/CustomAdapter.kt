package com.example.p2pcs_app.CarBookings.CarReservations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.p2pcs_app.Explore.MyData
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_car_reservation_card.view.*
import kotlinx.android.synthetic.main.activity_car_reservation_details.view.*

class CustomAdapter(

    private var myData: ArrayList<com.example.p2pcs_app.CarBookings.CarReservations.MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var username=view.Username
        var targa=view.targa
        var giorno=view.giorno
        var stato=view.Stato
        var contatta=view.Contatta

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_car_reservation_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.username.text="Elyss"
        p0.targa.text="Fiat Tipo"
        p0.giorno.text="2019-08-19"
        p0.stato.text=myData[p1].str1
        p0.contatta.visibility=View.INVISIBLE

    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



