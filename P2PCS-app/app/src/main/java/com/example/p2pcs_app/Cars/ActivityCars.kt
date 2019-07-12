package com.example.p2pcs_app.Cars

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.p2pcs_app.R

import kotlinx.android.synthetic.main.activity_cars.*

class ActivityCars : AppCompatActivity() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cars)
        val recyclerView= findViewById<RecyclerView> (R.id.cars_recycler_view)
        val data_list=  ArrayList<MyData>()
        load_data(data_list)

        linearLayoutManager= LinearLayoutManager(this)

        customAdapter= CustomAdapter(data_list)


        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager=linearLayoutManager
            adapter=customAdapter

        }
    }

    fun load_data (data_list: ArrayList<MyData>){ //prova con 3 card

        var myData1= MyData(1, "lol", "lol", "lol")


        var myData2= MyData(2, "lol", "lol", "lol")


        var myData3= MyData(3, "lol", "lol", "lol")


        data_list.add(myData1)
        data_list.add(myData2)
        data_list.add(myData3)


    }

}
