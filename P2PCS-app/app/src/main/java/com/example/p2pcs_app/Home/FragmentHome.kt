package com.example.p2pcs_app.Home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.p2pcs_app.Cars.FragmentCars
import com.example.p2pcs_app.LeaderBoard.ActivityLeaderboard
import com.example.p2pcs_app.Mission.FragmentMission
import com.example.p2pcs_app.CarBookings.ActivityCarBooking
import com.example.p2pcs_app.Cars.ActivityCars


import com.example.p2pcs_app.R
import com.example.p2pcs_app.Search.ActivitySearch
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    /*private fun showDialog(title:String, message:String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.ok, null)
            .create()
            .show()
    }
*/
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


        val button3 = view.findViewById<Button>(R.id.b_secondLine)
        button3.setOnClickListener{
            val intent= Intent(requireContext(), FragmentMission::class.java) //Fare activity mission per scalare la classifica
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