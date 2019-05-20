package com.example.p2pcs_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.p2pcs_app.login_register_cognito.CognitoSettings
import com.example.p2pcs_app.login_register_cognito.LoginActivity


class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, null)
        val datipersonali = view.findViewById<Button>(R.id.datipersonali)
        datipersonali.setOnClickListener{
            val intent= Intent(requireContext(), PersonalData::class.java)
            startActivity(intent)
        }

        val classifiche = view.findViewById<Button>(R.id.classifiche)
        classifiche.setOnClickListener{
            val intent= Intent(requireContext(), LeaderboardActivity::class.java)
            startActivity(intent)
        }

        val viaggi = view.findViewById<Button>(R.id.viaggi)
        viaggi.setOnClickListener{
            val intent= Intent(requireContext(), MyCarRequests::class.java)
            startActivity(intent)
        }

        val auto = view.findViewById<Button>(R.id.auto)
        auto.setOnClickListener{
            val intent= Intent(requireContext(), CarRequests::class.java)
            startActivity(intent)
        }

        val missioni = view.findViewById<Button>(R.id.missioni)
        missioni.setOnClickListener{
            val intent= Intent(requireContext(), ActivityMission::class.java) //TO DO
            startActivity(intent)
        }

        val premi = view.findViewById<Button>(R.id.premi)
        premi.setOnClickListener{
            val intent= Intent(requireContext(), Coupons::class.java)
            startActivity(intent)
        }

        val logout = view.findViewById<Button>(R.id.logoutbutton)
        logout.setOnClickListener{
            //signout
            val cognitosettings= CognitoSettings(context as Context)
            cognitosettings.getUserPool().currentUser.signOut()
            //redirect to login
            val intent= Intent(requireContext(), LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //clear all previous activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        //set username
        val context=requireContext()
        val prefs = context.getSharedPreferences(R.string.shared_preferences.toString(), 0)
        view.findViewById<TextView>(R.id.username).text=prefs.getString("username","vuoto")

        return view
    }



}
