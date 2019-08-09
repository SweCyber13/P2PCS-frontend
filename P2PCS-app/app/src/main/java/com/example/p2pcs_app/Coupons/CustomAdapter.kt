package com.example.p2pcs_app.Coupons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_coupon_card.view.*

class CustomAdapter(

   private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var nome=view.nome
        var titolo=view.titolo
        var punti=view.punti
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_coupon_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        //setto campi della card
        p0.nome.text=myData[p1].nome
        p0.titolo.text=myData[p1].titolo
        p0.punti.text=myData[p1].punti

    }


    override fun getItemCount(): Int {
        return myData.size
    }


}