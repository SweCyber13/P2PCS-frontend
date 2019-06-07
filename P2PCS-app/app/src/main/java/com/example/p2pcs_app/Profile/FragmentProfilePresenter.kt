package com.example.p2pcs_app.Profile

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.example.p2pcs_app.Model.Model
import com.example.p2pcs_app.Model.User
import com.example.p2pcs_app.ModelCallback
import com.example.p2pcs_app.R

class FragmentProfilePresenter(private var view: FragmentProfileContract.View, private var context: Context): FragmentProfileContract.Presenter {
    override fun getUserinfo() {
            try {
                var user=Model.getUser(context, "Matteo",object : ModelCallback<User> {
                    override fun onSuccess(result:User) {
                        if(view!=null) { //check if activity is still in execution
                            Log.i("ciao","entering presenter")
                            view.displayUserinfo(result.username)
                            Log.i("ciao","presenter success")
                        }

                    }
                }) //prendere username da sharedpreferences
            } catch (error: VolleyError) {
                //handle the error
            }
    }


}