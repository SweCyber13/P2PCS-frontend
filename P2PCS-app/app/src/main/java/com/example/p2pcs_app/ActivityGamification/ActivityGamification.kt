package com.example.p2pcs_app.ActivityGamification

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.Explore.MyData
import com.example.p2pcs_app.R

class ActivityGamification : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_prize_section) // in realtà è activity
    }

}
