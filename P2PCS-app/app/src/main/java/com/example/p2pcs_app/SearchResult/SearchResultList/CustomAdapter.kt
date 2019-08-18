package com.example.p2pcs_app.SearchResult.SearchResultList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_search_result_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var model=view.model
        var marca=view.marca
        var rate=view.rate //costo
        var city=view.city
        var username= view.username
        var prenota= view.reserve
        var targa=view.InTarga
        var punti=view.punti
        var bottone=view.reserve

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_search_result_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.prenota.setOnClickListener{
            //far comparire toast prenotazione effettuata
        }
        //setto campi della card
        p0.city.text=myData[p1].sindirizzo
        p0.username.text=myData[p1].susername
        p0.marca.text=myData[p1].smodello
        p0.model.text=myData[p1].smarca
        p0.rate.text=myData[p1].scosto+"€"
        p0.targa.text=myData[p1].starga
        p0.punti.text=myData[p1].strpunti
        p0.bottone.setOnClickListener{
            p0.bottone.text="inviata!"
            p0.bottone.isEnabled=false
        }
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



