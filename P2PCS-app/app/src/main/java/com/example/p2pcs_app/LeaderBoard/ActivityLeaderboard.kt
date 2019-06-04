package com.example.p2pcs_app.LeaderBoard

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R


class ActivityLeaderboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val viewPager = findViewById<ViewPager>(R.id.viewPagerLeaderboard)
        if (viewPager != null) {
            val adapter = ViewPagerAdapter(supportFragmentManager)
            viewPager.adapter = adapter
        }
    }
}