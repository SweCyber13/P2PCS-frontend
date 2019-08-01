package com.example.p2pcs_app.SearchResult.SearchResultMap

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.p2pcs_app.R
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition





class FragmentSearchResultMap : Fragment(), OnMapReadyCallback{

    var supportmap : SupportMapFragment? = null

    override fun onMapReady(p0: GoogleMap?) {
        //set pin torre archimede e zoom
        val sydney = LatLng(45.411559, 11.887451)
        p0!!.addMarker(
            MarkerOptions().position(sydney)
                .title("Fiat Punto di SweCyber13")
        )
        val cameraPosition = CameraPosition.Builder()
            .target(sydney)
            .zoom(17f).build()
        //p0!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        p0.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_search_result_map, container, false)

        supportmap= this.childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        if(supportmap==null) {
            val newmap=SupportMapFragment.newInstance()
            supportmap=newmap
            childFragmentManager.beginTransaction().add(R.id.map, newmap).commit()
        }
        supportmap!!.getMapAsync(this)
        return view
    }
}