package com.example.p2pcs_app.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.p2pcs_app.SearchResult.ActivitySearchresult
import com.example.p2pcs_app.Cars.FragmentCars
import com.example.p2pcs_app.LeaderBoard.ActivityLeaderboard
import com.example.p2pcs_app.Mission.FragmentMission
import com.example.p2pcs_app.CarPage.ActivityCarPage



import com.example.p2pcs_app.R


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, null)


        val button1 = view.findViewById<Button>(R.id.find_car)
        button1.setOnClickListener{
            val intent= Intent(requireContext(), ActivityLeaderboard::class.java)
            startActivity(intent)
        }



        val button2 = view.findViewById<Button>(R.id.share_car)
        button2.setOnClickListener{
            val intent= Intent(requireContext(), FragmentCars::class.java)
            startActivity(intent)
        }


        val button3 = view.findViewById<Button>(R.id.b_secondLine)
        button3.setOnClickListener{
            val intent= Intent(requireContext(), FragmentMission::class.java)
            startActivity(intent)
        }



        val button4 = view.findViewById<Button>(R.id.b_thirdLine)
        button4.setOnClickListener{
            val intent= Intent(requireContext(), ActivityLeaderboard::class.java)
            startActivity(intent)
        }


        val button5 = view.findViewById<Button>(R.id.provatab)
        button5.setOnClickListener{
            val intent= Intent(requireContext(), ActivityCarPage::class.java)
            startActivity(intent)
        }


        return view




    }


}