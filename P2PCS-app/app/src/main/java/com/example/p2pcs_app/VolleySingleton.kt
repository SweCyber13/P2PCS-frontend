package com.example.p2pcs_app

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class VolleySingleton private constructor(context: Context) {
    val requestQueue: RequestQueue

    init {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext())
    }

    companion object {

        private var instance: VolleySingleton? = null

        fun getInstance(context: Context): VolleySingleton {
            if (instance == null) {
                instance = VolleySingleton(context)
            }
            return instance!!
        }
    }
}