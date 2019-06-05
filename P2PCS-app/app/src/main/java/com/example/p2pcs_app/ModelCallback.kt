package com.example.p2pcs_app

import com.example.p2pcs_app.Model.User

interface ModelCallback {
    fun onSuccess(result: User)
}