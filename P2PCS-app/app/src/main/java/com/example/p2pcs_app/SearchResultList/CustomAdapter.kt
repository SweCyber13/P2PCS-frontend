package com.example.p2pcs_app.SearchResultList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.p2pcs_app.SearchResultList.MyData
import com.example.p2pcs_app.R

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var imageView=view.findViewById<ImageView>(R.id.car)
        var model=view.findViewById<TextView>(R.id.model)
        var marca=view.findViewById<TextView>(R.id.marca)
        var rate=view.findViewById<TextView>(R.id.rate)
        var city=view.findViewById<TextView>(R.id.city)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_search_result_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.model.text="Ciao"
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



