package com.example.p2pcs_app

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView


class EditCar : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car)

        val textView = findViewById<View>(R.id.TitleOffer1) as TextView
        val textView1= findViewById<View>(R.id.Company1) as TextView
        val textView2 = findViewById<View>(R.id.Targa) as TextView
        val textView3 = findViewById<View>(R.id.AnnoProduzione) as TextView

        val dialog = AlertDialog.Builder(this).create()
        val dialog1 = AlertDialog.Builder(this).create()
        val dialog2 = AlertDialog.Builder(this).create()
        val dialog3 = AlertDialog.Builder(this).create()

        val editText = EditText(this)
        val editText1 = EditText(this)
        val editText2 = EditText(this)
        val editText3 = EditText(this)

        dialog.setTitle("Edit the text")
        dialog.setView(editText)
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT") { dialog, which ->
            textView.text = editText.text
        }

        textView.setOnClickListener {
            editText.setText(textView.text)
            dialog.show()
        }

        dialog1.setTitle("Edit the text")
        dialog1.setView(editText1)
        dialog1.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT") { dialog1, which ->
            textView1.text = editText1.text
        }

        textView1.setOnClickListener {
            editText1.setText(textView1.text)
            dialog1.show()
        }

        dialog2.setTitle("Edit the text")
        dialog2.setView(editText2)
        dialog2.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT") { dialog2, which ->
            textView2.text = editText2.text
        }

        textView2.setOnClickListener {
            editText2.setText(textView2.text)
            dialog2.show()
        }

        dialog3.setTitle("Edit the text")
        dialog3.setView(editText3)
        dialog3.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT") { dialog3, which ->
            textView3.text = editText3.text
        }

        textView3.setOnClickListener {
            editText3.setText(textView3.text)
            dialog3.show()
        }

    }


}