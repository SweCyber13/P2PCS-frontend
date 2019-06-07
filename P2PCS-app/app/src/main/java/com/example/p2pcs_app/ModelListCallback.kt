package com.example.p2pcs_app

interface ModelListCallback<T> {
    fun onSuccess(result: ArrayList<T>)
}