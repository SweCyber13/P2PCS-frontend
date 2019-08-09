package com.example.p2pcs_app.DataClass

data class Car(
    var targa: String="",
    var proprietario: String="",
    var marca: String="",
    var modello: String="",
    var anno: String="",
    var cavalli: Int=0,
    var cilindrata: Int=0,
    var chilometraggio: Int=0,
    var raggio: Int=0,
    var tariffa: Double=0.0,
    var lat: Double=0.0,
    var lng: Double=0.0,
    var indririzzo: String=""


)