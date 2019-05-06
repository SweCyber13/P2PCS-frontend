package com.example.p2pcs_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.support.v4.app.Fragment
import android.support.v4.app.Person
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                textMessage.setText(R.string.title_search)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cars -> {
                textMessage.setText(R.string.title_cars)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                textMessage.setText(R.string.title_explore)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                textMessage.setText(R.string.title_profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //load different fragment when a navigation button is pressed
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_profile -> loadFragment(ProfileFragment())
            R.id.navigation_search -> loadFragment(SearchFragment())
            R.id.navigation_cars -> loadFragment(CarsFragment())
            R.id.navigation_explore -> loadFragment(ExploreFragment())
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
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, PersonalData::class.java)
            startActivity(intent)

        }
        //textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(this)
    }
}
