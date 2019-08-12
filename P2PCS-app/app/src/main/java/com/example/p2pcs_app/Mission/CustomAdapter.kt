package com.example.p2pcs_app.Mission
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_mission_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var titolo=view.missionTitle
        var descr=view.missionDescription
        var puntiRank= view.puntiRank
        var puntiBuoni= view.puntiBuoni

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_mission_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        //setto campi della card
        p0.titolo.text=myData[p1].titolo
        p0.descr.text=myData[p1].descr
        p0.puntiRank.text=myData[p1].puntiRank
        p0.puntiBuoni.text=myData[p1].puntiBuoni


    }


    override fun getItemCount(): Int {
        return myData.size
    }


}