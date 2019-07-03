package com.example.p2pcs_app

import android.content.Context
import com.example.p2pcs_app.Model.DataClass
import com.example.p2pcs_app.Model.User

abstract class URLBuilder(urlString: String){
    //f build part
    abstract fun buildURL(user: DataClass, context: Context):URL //in realtà qui tipo di ritorno sara url


}