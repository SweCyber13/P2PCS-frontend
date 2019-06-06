package com.example.p2pcs_app.CarBookings.CarReservations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.p2pcs_app.Explore.MyData
import com.example.p2pcs_app.R

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var username=view.findViewById<TextView>(R.id.username)
        var targa=view.findViewById<TextView>(R.id.targa)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_car_reservation_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.targa.text="Ciao"
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



