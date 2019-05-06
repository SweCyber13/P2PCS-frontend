package com.example.datepicker

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // button click to show DatePickerDialog
        startDateBtn.setOnClickListener{
            val dpd = DatePickerDialog (this, DatePickerDialog.OnDateSetListener{ view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                dateTv.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
            }, year, month, day)

            dpd.show()
        }

        endDateBtn.setOnClickListener{
            val dpd = DatePickerDialog (this, DatePickerDialog.OnDateSetListener{ view: DatePicker?, mYear: Int, mMonth: Int, mDay: Int ->
                dateTp.setText(""+ mDay +"/"+ mMonth +"/"+ mYear)
            }, year, month, day)

            dpd.show()
        }

    }
}
