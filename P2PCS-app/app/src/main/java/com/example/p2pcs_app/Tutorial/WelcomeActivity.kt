package com.example.p2pcs_app.Tutorial

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.p2pcs_app.R
import com.example.p2pcs_app.Search.ActivitySearch
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_welcome.*




class WelcomeActivity: Fragment(){
   // private lateinit var introViewPagerAdapter: IntroScreenViewPagerAdapter
    private lateinit var introBullets: Array<TextView>
    private lateinit var introSliderLayouts: IntArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_welcome, null)


        val button1 = view.findViewById<Button>(R.id.gh)
        button1.setOnClickListener{
            val intent= Intent(requireContext(), Screen1::class.java)
            startActivity(intent)
        }



        val button2 = view.findViewById<Button>(R.id.gm)
        button2.setOnClickListener{
            val intent= Intent(requireContext(), Screen2::class.java)
            startActivity(intent)
        }







        return view




    }

}