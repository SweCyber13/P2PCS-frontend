package com.example.p2pcs_app

import android.content.Context
import com.example.p2pcs_app.Model.User

class DirectorURLBuilder(builder: URLBuilder){
    //f construct(): set, construct, get
    lateinit var builder:URLBuilder

    fun  setURL(){
        println("Ciao mondo!")
    }

    fun  construct(user: User, context: Context):URL{
        //invoca buildURL
        println("Ciao mondo!")
        val urlprova=builder.buildURL(user,context)
        return urlprova
    }

    fun  getURL(){//tipo di ritorno deve essere url
        println("Ciao mondo!")

    }



}