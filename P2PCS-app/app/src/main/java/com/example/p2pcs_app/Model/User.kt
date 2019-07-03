package com.example.p2pcs_app.Model

import java.util.*

class User : DataClass {
    lateinit var username:String
    lateinit var nome:String
    lateinit var cognome:String
    lateinit var mail:String
    var punti_rank:Int =0
    var punti_buoni:Int =0
    var eta:Int =0
    var sesso:Int =0
    lateinit var citta:String
    lateinit var data_rilascio_patente: String
    lateinit var numero_patente: String
    lateinit var occupazione: String

}