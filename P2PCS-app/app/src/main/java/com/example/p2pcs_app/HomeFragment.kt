package com.example.p2pcs_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home, null)


        val button1 = view.findViewById<Button>(R.id.find_car)
        button1.setOnClickListener{
            val intent= Intent(requireContext(), LeaderboardActivity::class.java)
            startActivity(intent)
        }



        val button2 = view.findViewById<Button>(R.id.share_car)
        button2.setOnClickListener{
            val intent= Intent(requireContext(), CarsFragment::class.java)
            startActivity(intent)
        }











        val button3 = view.findViewById<Button>(R.id.b_secondLine)
        button3.setOnClickListener{
            val intent= Intent(requireContext(), LeaderboardActivity::class.java)
            startActivity(intent)
        }



        val button4 = view.findViewById<Button>(R.id.b_thirdLine)
        button4.setOnClickListener{
            val intent= Intent(requireContext(), LeaderboardActivity::class.java)
            startActivity(intent)
        }

        return view




    }


}