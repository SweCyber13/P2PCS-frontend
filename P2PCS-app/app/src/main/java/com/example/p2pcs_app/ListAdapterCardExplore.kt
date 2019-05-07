package com.example.p2pcs_app

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.support.v7.widget.RecyclerView

class ListAdapter(private val list: List<Offer>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val offer: Offer = list[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.card_explore, parent, false)) {
    private var mBackground: LinearLayout?=null
    private var mCompanyView: TextView? = null
    private var mTitleView: TextView? = null




    init {
        //mBackground= itemView.findViewById(R.id.card_background)
        mCompanyView = itemView.findViewById(R.id.NomeAzienda)
        mTitleView = itemView.findViewById(R.id.TitoloOfferta)
    }

    fun bind(offer: Offer) {
        mCompanyView?.text = offer.azienda
        mTitleView?.text = offer.titolo


    }

}

