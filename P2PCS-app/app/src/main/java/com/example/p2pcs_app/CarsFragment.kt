package com.example.p2pcs_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class CarsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cars, null)
        val button = view.findViewById<Button>(R.id.InfoCar)
        button.setOnClickListener{
            val intent= Intent(requireContext(), visualizza_macchina::class.java)
            startActivity(intent)
        }


        return view
    }



}
