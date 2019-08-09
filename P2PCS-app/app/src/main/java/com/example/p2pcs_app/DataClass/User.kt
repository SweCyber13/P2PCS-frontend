package com.example.p2pcs_app.DataClass

import java.util.*

data class User(
    var nome: String = "",
    var cognome: String = "",
    var email: String = "",
    var citta: String? = "",
    var rank: Long=0,
    var buoni: Long=0,
    var eta: Int=0,
    var sesso: String="",
    var patente: String="",
    var rilascio_patente: String="",
    var occupazione: String=""
)