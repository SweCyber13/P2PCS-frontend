package com.example.p2pcs_app.LeaderBoard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.p2pcs_app.LeaderBoard.GlobalLeaderboard.FragmentGlobalLeaderboard
import com.example.p2pcs_app.LeaderBoard.LocalLeaderboard.FragmentLocalLeaderboard

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 1

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentGlobalLeaderboard()
            1 -> fragment = FragmentLocalLeaderboard()

        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position==0)
            return "Classifica Globale"
        else
            return "Classifica Locale"
    }
}