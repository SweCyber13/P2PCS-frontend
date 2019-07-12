package com.example.p2pcs_app.Zutilities

import android.content.Context
import com.example.p2pcs_app.Model.User
import com.example.p2pcs_app.ModelCallback

class UserModel{
    fun userRequest(user: User, context:Context, callback: ModelCallback<User>){
        //utilizzo URLBuilder per costruire URL
        var builder= URLProfileBuilder("users/read.php?USERNAME=$user.username")
        var director= DirectorURLBuilder(builder)
        var urlProfile=director.construct(user, context)



        //istanzia classe APIRequestHandle che diventa istanziabile
        //chiamo metodo makeRequest di APIReqquestHandler
        //Attendo risposta, finisce
        //Quando arriva risposta chiamo il parser e costruisco User
        //Chiamo onSuccess oppure chiamo onFailure



    }

}