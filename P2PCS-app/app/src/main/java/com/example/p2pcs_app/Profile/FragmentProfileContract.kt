package com.example.p2pcs_app.Profile

import com.example.p2pcs_app.BasePresenter
import com.example.p2pcs_app.BaseView

interface FragmentProfileContract {
    interface View {
        fun displayUserinfo(Username:String)
    }

    interface Presenter {
        fun getUserinfo()
    }
}