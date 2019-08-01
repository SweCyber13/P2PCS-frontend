package com.example.p2pcs_app.EditName


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.p2pcs_app.R


class EditNameFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_name, null)
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener{
            this.dismiss()
        }
        return view
    }

}
