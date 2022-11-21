package com.thiaagodev.components

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.thiaagodev.components.R.id.spinner_static

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSnack = findViewById<Button>(R.id.button_snack)
        val buttonGetSpinner = findViewById<Button>(R.id.button_get_spinner)
        val buttonSetSpinner = findViewById<Button>(R.id.button_set_spinner)
        val spinnerStatic = findViewById<Spinner>(spinner_static)

        buttonToast.setOnClickListener(this)
        buttonSnack.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)

        spinnerStatic.onItemSelectedListener = this

        loadSpinner()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "TOAST", Toast.LENGTH_LONG)

                val layout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = layout

                toast.show()
            }

            R.id.button_snack -> {
                val snack =
                    Snackbar.make(findViewById(R.id.linear_root), "Snack", Snackbar.LENGTH_SHORT)

                snack.setAction("Desfazer", View.OnClickListener {
                    toast("Desfeito")
                })

                snack.setActionTextColor(Color.BLUE)
                snack.setBackgroundTint(Color.GRAY)

                snack.show()
            }

            R.id.button_get_spinner -> {
                val selectedItem = findViewById<Spinner>(spinner_static).selectedItem
                val selectedItemId = findViewById<Spinner>(spinner_static).selectedItemId
                val selectedItemPosition = findViewById<Spinner>(spinner_static).selectedItemPosition

                toast("Position: $selectedItemId: $selectedItem ")

            }

            R.id.button_set_spinner -> {
                val spinnerStatic = findViewById<Spinner>(spinner_static)

                spinnerStatic.setSelection(2)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            spinner_static -> {
                val text = parent.getItemAtPosition(position)
                toast(text.toString())
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        toast("Nothing")
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun loadSpinner() {
        val spinnerDynamic = findViewById<Spinner>(R.id.spinner_dynamic)
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinnerDynamic.adapter = adapter

    }

}