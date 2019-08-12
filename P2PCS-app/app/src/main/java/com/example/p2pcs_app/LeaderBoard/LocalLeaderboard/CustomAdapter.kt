package com.example.p2pcs_app.LeaderBoard.LocalLeaderboard



import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_local_leaderboard_card.view.*

class CustomAdapter(

    private var myData: ArrayList<MyData>

) : RecyclerView.Adapter<CustomAdapter.MyViewHolder> () {


    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view){

        var position=view.position
        var username=view.username
        var rank=view.rank
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_global_leaderboard_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        //setto campi della card
        p0.position.text=myData[p1].position
        p0.username.text=myData[p1].username
        p0.rank.text=myData[p1].rank

    }


    override fun getItemCount(): Int {
        return myData.size
    }


}