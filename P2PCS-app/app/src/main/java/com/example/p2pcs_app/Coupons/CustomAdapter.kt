package com.example.p2pcs_app.Coupons

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.CouponDetails.ActivityCouponsDetails
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_coupon_card.view.*

class CustomAdapter(

   private var myData: ArrayList<MyData>
   , context: Context

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {
    val mcontext= context


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var nome=view.nome
        var titolo=view.titolo
        var button= view.info

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_coupon_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.button.setOnClickListener{
            val intent= Intent(mcontext, ActivityCouponsDetails::class.java) //Activity dettagli coupon
          //  intent.putExtra("Coupon", myData[p1].nome) //passo la nome coupon
            mcontext.startActivity(intent)
        }

        //setto campi della card
        p0.nome.text=myData[p1].nome
        p0.titolo.text=myData[p1].titolo


    }


    override fun getItemCount(): Int {
        return myData.size
    }


}