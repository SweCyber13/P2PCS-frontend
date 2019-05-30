package com.example.p2pcs_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class FragmentSearchResultMap : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search_result_map, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setText(R.string.prova2)

        val imageView = view.findViewById<ImageView>(R.id.imageView5)
        imageView.setImageResource(R.mipmap.ic_launcher)

        return view
    }
}