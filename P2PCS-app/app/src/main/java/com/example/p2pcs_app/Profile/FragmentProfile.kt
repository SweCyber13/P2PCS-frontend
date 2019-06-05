package com.example.p2pcs_app.Profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.p2pcs_app.MainActivity
import com.example.p2pcs_app.R
import com.example.p2pcs_app.RequestQueueListener
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONArray
import org.json.JSONObject

class FragmentProfile : Fragment(), FragmentProfileContract.View {
    lateinit  var mypresenter: FragmentProfileContract.Presenter
    var listener: RequestQueueListener?=null
    override fun displayUserinfo(Username:String) {
        //setto tutte le textview
        if(view!=null){
            val usernameview = view!!.findViewById<TextView>(R.id.Username)
            usernameview.setText(Username)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, null)

        mypresenter=FragmentProfilePresenter(this,listener?.getRequestQueue()!!)

        mypresenter.getUserinfo()
        return view
    }

    /*override fun onStart() {
        super.onStart()
        mypresenter.getUserinfo()
    }*/



    fun attach(listener:RequestQueueListener){
        this.listener=listener
    }

}
