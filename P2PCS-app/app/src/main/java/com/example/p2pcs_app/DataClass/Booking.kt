package com.example.p2pcs_app.DataClass

data class Booking(
    var id: Int=0,
    var proprietario: String="",
    var richiedente: String="",
    var targa: String="",
    var data: String="",
    var mininizio: Int=0,
    var minfine: Int=0,
    var indirizzopartenza: String="",
    var indirizzoarrivo: String="",
    var latpartenza: Int=0,
    var latarrivo: Int=0,
    var lngpartenza: Int=0,
    var lngarrivo: Int=0,
    var costo: Double=0.0,
    var stato: String=""
)