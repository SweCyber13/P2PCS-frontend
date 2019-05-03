package com.example.p2pcs_cyber

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    var listOfusers: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adding items in list
        for (i in 0..4) {
            val user = Users()
            user.id = i
            user.login = "Eyehunt $i"
            listOfusers!!.add(user)
        }
        mRecyclerView = findViewById(R.id.my_recycler_view)
        var mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = Myadapter(listOfusers)
        mRecyclerView!!.adapter = mAdapter

    }
}