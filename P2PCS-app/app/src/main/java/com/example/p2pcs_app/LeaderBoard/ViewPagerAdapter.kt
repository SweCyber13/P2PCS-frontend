package com.example.p2pcs_app.LeaderBoard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.p2pcs_app.LeaderBoard.GlobalLeaderboard.GlobalFragmentLeaderboard
import com.example.p2pcs_app.LeaderBoard.LocalLeaderboard.LocalFragmentLeaderboard

class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = GlobalFragmentLeaderboard()
            1 -> fragment = LocalFragmentLeaderboard()

        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}