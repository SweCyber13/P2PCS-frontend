package com.example.p2pcs_app.Cars

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.p2pcs_app.CarNew.ActivityNewCar
import com.example.p2pcs_app.EditCar.ActivityEditCar
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_cars_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

, context: Context
) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {
    val mcontext= context

    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){
        var marca=view.textMarca
        var modello=view.textModel
        var year= view.textYear
        var targa=view.textTarga
        var button= view.otherInfo
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cars_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.button.setOnClickListener{
            val intent= Intent(mcontext, ActivityEditCar::class.java)
            intent.putExtra("VEHICLE", 1) //inserire le informazioni da passare da mydata info macchina
            mcontext.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return myData.size
    }


}



