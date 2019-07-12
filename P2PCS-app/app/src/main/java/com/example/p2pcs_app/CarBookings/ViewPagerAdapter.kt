package com.example.p2pcs_app.CarBookings

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.p2pcs_app.CarBookings.CarRequests.FragmentCarRequests
import com.example.p2pcs_app.CarBookings.CarReservations.FragmentCarReservations

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentCarRequests()
            1 -> fragment = FragmentCarReservations()

        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position==0)
            return "Richieste"
        else
            return "Prenotazioni"
    }
}