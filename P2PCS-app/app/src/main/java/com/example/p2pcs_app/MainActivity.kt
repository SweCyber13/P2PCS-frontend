package com.example.p2pcs_app

import android.os.Bundle
import android.view.MenuItem
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.support.v4.app.Fragment
import com.example.p2pcs_app.Cars.FragmentCars
import com.example.p2pcs_app.Explore.FragmentExplore
import com.example.p2pcs_app.Home.HomeFragment
import com.example.p2pcs_app.Profile.FragmentProfile


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

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
            R.id.navigation_reservation -> loadFragment(FragmentCars())
            R.id.navigation_gift -> loadFragment(FragmentExplore())
        }

        return true

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
        //set action bar buttons

        //textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(this)
        //load fragment_home default
        loadFragment(HomeFragment())


        //test userpreference
        //val prefs = this.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        //Toast.makeText(this, prefs.getString("username","vuoto") , Toast.LENGTH_LONG).show()
    }
}
