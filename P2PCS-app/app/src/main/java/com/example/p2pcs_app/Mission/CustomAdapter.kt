package com.example.p2pcs_app.Mission

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.p2pcs_app.Mission.MyData
import com.example.p2pcs_app.R

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var description=view.findViewById<TextView>(R.id.titlemission)
        var imageView=view.findViewById<ImageView>(R.id.missioncar)
        var point=view.findViewById<ImageView>(R.id.pointmission)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_mission_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.description.text="Ciao"
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



