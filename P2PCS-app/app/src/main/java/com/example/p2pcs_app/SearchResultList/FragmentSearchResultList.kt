package com.example.p2pcs_app.SearchResultList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p2pcs_app.R

class FragmentSearchResultList : Fragment() {

    //parametri per la recyclerView
    private var recyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var customAdapter: CustomAdapter? =null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search_result_list, null)


        val recyclerView= view.findViewById<RecyclerView> (R.id.recycler_view)
        val data_list=  ArrayList<MyData>()
        load_data(data_list)

        linearLayoutManager= LinearLayoutManager(requireContext())

        customAdapter= CustomAdapter(data_list)


        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager=linearLayoutManager
            adapter=customAdapter

        }

        return view
    }

    fun load_data (data_list: ArrayList<MyData>){ //prova con 3 card

        var myData1= MyData(1, "lol", "lol", "lol", "lol")


        var myData2= MyData(2, "lol", "lol", "lol", "lol")


        var myData3= MyData(3, "lol", "lol", "lol", "lol")


        data_list.add(myData1)
        data_list.add(myData2)
        data_list.add(myData3)


    }


}