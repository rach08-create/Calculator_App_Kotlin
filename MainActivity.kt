package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView?=null
    private var tvAgeInMinutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button=findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
            tvSelectedDate=findViewById(R.id.tvSelectedDate)
            tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{_,selectedYear,selectedMonth,selectedDayOfMonth ->
            Toast.makeText(this,"DatePicker works",Toast.LENGTH_LONG)
            val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.setText(selectedDate)
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            theDate?.let{
                val selectedDateInMinutes=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateInMinutes=currentDate.time/60000
                    val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                    tvAgeInMinutes?.text=differenceInMinutes.toString()
                }


            }

        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}