package com.example.p2pcs_app

import android.content.Context
import com.example.p2pcs_app.Model.DataClass
import com.example.p2pcs_app.Model.User

class URLProfileBuilder(urlString: String):URLBuilder(urlString) {

    override fun buildURL(user: DataClass, context: Context):URL{ //campo di tipo user da cui estrae, invoca builPartOne
        println("Sono nella spttpclasse!")
        val urlprova=URL("lol")
        return urlprova
    }

    fun builPartOne(){//costruisce pt stringa url

    }
}