package com.example.p2pcs_app.CarBookings

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R


class ActivityCarBooking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_booking)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        if (viewPager != null) {
            val adapter = ViewPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter
        }

    }
}