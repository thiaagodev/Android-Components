package com.thiaagodev.components

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.snackbar.Snackbar
import com.thiaagodev.components.R

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSnack = findViewById<Button>(R.id.button_snack)
        val buttonGetSpinner = findViewById<Button>(R.id.button_get_spinner)
        val buttonSetSpinner = findViewById<Button>(R.id.button_set_spinner)
        val spinnerStatic = findViewById<Spinner>(R.id.spinner_static)
        val seekBar = findViewById<SeekBar>(R.id.seekbar)
        val buttonSetSeekBar = findViewById<Button>(R.id.button_set_seekbar)
        val buttonGetSeekBar = findViewById<Button>(R.id.button_get_seekbar)
        val switch = findViewById<SwitchCompat>(R.id.switch_on_off)
        val checkBox = findViewById<CheckBox>(R.id.check_on_off)

        buttonToast.setOnClickListener(this)
        buttonSnack.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)
        buttonGetSeekBar.setOnClickListener(this)
        buttonSetSeekBar.setOnClickListener(this)

        spinnerStatic.onItemSelectedListener = this
        seekBar.setOnSeekBarChangeListener(this)

        switch.setOnCheckedChangeListener(this)
        checkBox.setOnCheckedChangeListener(this)

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
                val selectedItem = findViewById<Spinner>(R.id.spinner_static).selectedItem
                val selectedItemId = findViewById<Spinner>(R.id.spinner_static).selectedItemId
                val selectedItemPosition =
                    findViewById<Spinner>(R.id.spinner_static).selectedItemPosition

                toast("Position: $selectedItemId: $selectedItem ")

            }

            R.id.button_set_spinner -> {
                val spinnerStatic = findViewById<Spinner>(R.id.spinner_static)

                spinnerStatic.setSelection(2)
            }

            R.id.button_get_seekbar -> {
                val seekBarValue = findViewById<SeekBar>(R.id.seekbar).progress
                toast("Seekbar: $seekBarValue")
            }

            R.id.button_set_seekbar -> {
                val seekBar = findViewById<SeekBar>(R.id.seekbar)
                seekBar.progress = 15
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.switch_on_off -> {
                val switch = findViewById<SwitchCompat>(R.id.switch_on_off)

                // switch.isChecked = true

                toast("Switch ${if (isChecked) "true" else "false"}")
            }

            R.id.check_on_off -> {
                val checkBox = findViewById<CheckBox>(R.id.check_on_off)
                val a = checkBox.isChecked
                toast("Checkbox ${if (isChecked) "true" else "false"}")
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val textSeekBar = findViewById<TextView>(R.id.text_seekbar_value)

        textSeekBar.text = "Valor seekbar: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        toast("Track stoped")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_static -> {
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