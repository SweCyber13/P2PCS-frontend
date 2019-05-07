
package com.example.p2pcs_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore.*

data class Offer(val azienda: String, val titolo: String, val image: String)

class CardExplore : Fragment() {

    private val offerAvailable = listOf(
        Offer("Carrefour", "Sconto 20%", "carrefour.jpg"  ),
        Offer("TheSpace", "Biglietto Gratis", "thespace.jpg"),
        Offer("Carrefour", "Sconto 20%", "carrefour.jpg"  ),
        Offer("TheSpace", "Biglietto Gratis", "thespace.jpg"),
        Offer("Carrefour", "Sconto 20%", "carrefour.jpg"  ),
        Offer("TheSpace", "Biglietto Gratis", "thespace.jpg"),
        Offer("Carrefour", "Sconto 20%", "carrefour.jpg"  ),
        Offer("TheSpace", "Biglietto Gratis", "thespace.jpg"),
        Offer("Carrefour", "Sconto 20%", "carrefour.jpg"  ),
        Offer("TheSpace", "Biglietto Gratis", "thespace.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_explore, container, false)


    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(offerAvailable)
        }
    }*/

    companion object {
        fun newInstance(): CardExplore = CardExplore()
    }
}