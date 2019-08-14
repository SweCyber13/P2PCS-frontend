package com.example.p2pcs_app.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.p2pcs_app.ActivityGamification.ActivityGamification
import com.example.p2pcs_app.LeaderBoard.ActivityLeaderboard
import com.example.p2pcs_app.Cars.ActivityCars
import com.example.p2pcs_app.Explore.ActivityExplore
import com.example.p2pcs_app.Mission.ActivityMission
import com.example.p2pcs_app.Profile.ActivityPrize


import com.example.p2pcs_app.R
import com.example.p2pcs_app.Search.ActivitySearch
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)


        val button1 = view.findViewById<Button>(R.id.find_car)
        button1.setOnClickListener{
            val intent= Intent(requireContext(), ActivitySearch::class.java)
            startActivity(intent)
        }



        val button2 = view.findViewById<Button>(R.id.share_car)
        button2.setOnClickListener{
            val intent= Intent(requireContext(), ActivityCars::class.java)
            startActivity(intent)
        }

        val button3 = view.findViewById<Button>(R.id.b1)
        button3.setOnClickListener{
            val intent= Intent(requireContext(), ActivityPrize::class.java)
            startActivity(intent)
        }

        val button5 = view.findViewById<Button>(R.id.b2)
        button5.setOnClickListener{
            val intent= Intent(requireContext(), ActivityGamification::class.java)
            startActivity(intent)
        }




        val presentText = view.findViewById<TextView>(R.id.toPresent)
        presentText.setOnClickListener {
            val intent = Intent(requireContext(), ActivityGamification::class.java)
            startActivity(intent)
        }
        val pointsText = view.findViewById<TextView>(R.id.toPoints)
        pointsText.setOnClickListener{
            val intent= Intent(requireContext(), ActivityPrize::class.java)
            startActivity(intent)
        }
        val button4 = view.findViewById<Button>(R.id.b_thirdLine)
        button4.setOnClickListener{
            val intent= Intent(requireContext(), ActivityLeaderboard::class.java)
            startActivity(intent)
        }

        val prefs = requireContext().getSharedPreferences(R.string.shared_preferences.toString(), 0)
        val str_username=prefs.getString("username","")
        if(str_username=="SweElena"){
            view.third_part_title.text="Sei il 1 in classifica!"
        }
        if(str_username=="SweCyber13"){
            view.third_part_title.text="Sei il 14 in classifica!"
        }


        return view




    }


}