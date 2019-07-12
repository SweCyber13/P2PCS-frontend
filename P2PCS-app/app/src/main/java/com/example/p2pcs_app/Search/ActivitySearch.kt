package com.example.p2pcs_app.Search

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.p2pcs_app.R
import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import com.example.p2pcs_app.SearchResult.ActivitySearchresult
import kotlinx.android.synthetic.main.activity_other_user.*

import kotlinx.android.synthetic.main.activity_search.*

class ActivitySearch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search)

        val button = findViewById<Button>(R.id.search_search_car)
        button.setOnClickListener{
            val intent= Intent(this, ActivitySearchresult::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog(title:String, message:String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.ok, null)
            .create()
            .show()

    }

}
