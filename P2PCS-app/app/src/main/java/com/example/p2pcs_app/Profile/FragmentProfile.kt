package com.example.p2pcs_app.Profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.p2pcs_app.R

class FragmentProfile : Fragment(), FragmentProfileContract.View {
    lateinit  var mypresenter: FragmentProfileContract.Presenter
    //var listener: RequestQueueListener?=null
    override fun displayUserinfo(Username:String) {
        //setto tutte le textview
        if(view!=null){
            val usernameview = view!!.findViewById<TextView>(R.id.Username)
            usernameview.setText(Username)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, null)

        //mypresenter=FragmentProfilePresenter(this,listener?.getRequestQueue()!!)
        mypresenter=FragmentProfilePresenter(this,requireContext())

        mypresenter.getUserinfo()
        return view
    }

    /*override fun onStart() {
        super.onStart()
        mypresenter.getUserinfo()
    }*/



    /*fun attach(listener:RequestQueueListener){
        this.listener=listener
    }*/

}
