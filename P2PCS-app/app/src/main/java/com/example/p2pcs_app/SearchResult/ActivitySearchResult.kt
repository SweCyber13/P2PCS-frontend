package com.example.p2pcs_app.SearchResult

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R

class ActivitySearchresult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        if (viewPager != null) {
            val adapter = ResultViewPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter
        }
    }
}