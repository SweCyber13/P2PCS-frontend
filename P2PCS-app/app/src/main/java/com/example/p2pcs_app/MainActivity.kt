package com.example.p2pcs_app

import android.os.Bundle
import android.view.MenuItem
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.support.v4.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.CarBookings.ActivityCarBooking

import com.example.p2pcs_app.Home.HomeFragment
import com.example.p2pcs_app.Profile.FragmentProfile
import com.example.p2pcs_app.Coupons.ActivityCoupon


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, RequestQueueListener {

    //var fragmentProfile=FragmentProfile()
    //var fragmentbooking= ActivityCarBooking()

    var mqueue: RequestQueue?=null
    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_search)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_reservation -> {
                textMessage.setText(R.string.title_cars)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                textMessage.setText(R.string.title_explore)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_gift -> {
                textMessage.setText(R.string.title_profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //load different fragment when a navigation button is pressed
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_profile -> loadFragment(FragmentProfile())
            R.id.navigation_home -> loadFragment(HomeFragment())
            R.id.navigation_reservation -> loadFragment(ActivityCarBooking()) //da rinominare a fragment la classe
            //R.id.navigation_gift -> loadFragment(ActivityCoupon())
        }

        return true

    }

    override fun getRequestQueue(): RequestQueue {
        return mqueue!!
    }

    //load the selected fragment in the fragment container
    private fun loadFragment(fragment: Fragment): Boolean {
        if(fragment!=null)
        {
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment).commit()
            return true
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        mqueue=Volley.newRequestQueue(this)
        //set action bar buttons

        //textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(this)
        //load fragment_home default
        loadFragment(HomeFragment())

        //fragmentProfile.attach(this)
        //test userpreference
        //val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        //Toast.makeText(this, prefs.getString("username","vuoto") , Toast.LENGTH_LONG).show()
    }
}
