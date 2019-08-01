package com.example.p2pcs_app.Profile

interface FragmentProfileContract {
    interface View {
        fun displayUserinfo(Username:String)
    }

    interface Presenter {
        fun getUserinfo()
        fun getUserinfo2()
    }
}