package com.example.p2pcs_app.EditCarCalendar

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_edit_car_calendar_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

    , context: Context
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {
    val mcontext= context

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        var giorno=view.data
        var inizio=view.oraInizio
        var fine= view.oraFine
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_edit_car_calendar_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.giorno.text=myData[p1].giorno
        p0.inizio.text=myData[p1].inizio
        p0.fine.text=myData[p1].fine
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}