package com.example.p2pcs_app.SearchResult

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.p2pcs_app.R

class ActivitySearchresult : AppCompatActivity() {

    var latp=""
    var lonp=""
    var lata=""
    var lona=""
    var dataarr=""
    var minstart=""
    var minend=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val intentpassed = intent
        latp=intent.getStringExtra("LATP")
        lonp=intent.getStringExtra("LONP")
        lata=intent.getStringExtra("LATA")
        lona=intent.getStringExtra("LONA")
        dataarr=intent.getStringExtra("DAY")
        minstart=intent.getStringExtra("MINP")
        minend=intent.getStringExtra("MINA")

        Toast.makeText(this, latp+lonp+lata+lona, Toast.LENGTH_LONG).show()

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        if (viewPager != null) {
            val adapter = ViewPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter
        }
    }
}