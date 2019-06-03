package com.example.p2pcs_app.FragmentSearchResultList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.p2pcs_app.R

class FragmentSearchResultList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search_result_list, container, false)
        val textView = view.findViewById<TextView>(R.id.username)
        textView.setText(R.string.prova1)

        val imageView = view.findViewById<ImageView>(R.id.profil_immage)
        imageView.setImageResource(R.mipmap.ic_launcher)

        return view
    }
}
