package com.example.p2pcs_app.CarBookings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R


class ActivityCarBooking : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_car_booking, null)

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_booking)*/

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)

        if (viewPager != null) {
            val adapter = ViewPagerAdapter(this.childFragmentManager) //childfragment manager per handling di fragment nel fragment
            viewPager.adapter = adapter
        }

        return view

    }

    private fun showDialog(title:String, message:String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.ok, null)
            .create()
            .show()

    }
}