package com.thiaagodev.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, TimePicker.OnTimeChangedListener {

    private val mSimpleDate = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        val buttonDate = findViewById<Button>(R.id.button_date)
        val buttonTime = findViewById<Button>(R.id.button_time)
        val timePicker = findViewById<TimePicker>(R.id.timepicker)
        val buttonGetTime = findViewById<Button>(R.id.button_get_time)
        val buttonSetTime = findViewById<Button>(R.id.button_set_time)


        buttonDate.setOnClickListener(this)
        buttonTime.setOnClickListener(this)
        timePicker.setOnTimeChangedListener(this)
        buttonGetTime.setOnClickListener(this)
        buttonSetTime.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_date -> {

                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)

                DatePickerDialog(this, this, year, month, day).show()
            }

            R.id.button_time -> {
                TimePickerDialog(this, this, 1, 1, false).show()
            }

            R.id.button_get_time -> {
                val timePicker = findViewById<TimePicker>(R.id.timepicker)
                if (Build.VERSION.SDK_INT >= 23) {
                    val hour = timePicker.hour
                    val minute = timePicker.minute
                    toast("$hour:$minute")
                } else {
                    val hour = timePicker.currentHour
                    val minute = timePicker.currentMinute
                    toast("$hour:$minute")
                }
            }

            R.id.button_set_time -> {
                val timePicker = findViewById<TimePicker>(R.id.timepicker)

                if (Build.VERSION.SDK_INT >= 23) {
                    timePicker.hour = 20
                    timePicker.minute - 20
                } else {
                    timePicker.currentHour = 20
                    timePicker.currentMinute - 20
                }
            }
        }
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(year, month, dayOfMonth)

        findViewById<Button>(R.id.button_date).text = mSimpleDate.format(date.time)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}