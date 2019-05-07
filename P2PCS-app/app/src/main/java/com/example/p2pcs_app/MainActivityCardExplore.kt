package com.example.p2pcs_app

class MainActivityCardExplore : SingleFragmentActivityCardExplore() {
    override fun createFragment() = CardExplore.newInstance()
}
