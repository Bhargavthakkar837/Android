package com.example.currencyconverter

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount1: EditText
    private lateinit var spinnerCurrency1: Spinner
    private lateinit var editTextAmount2: EditText
    private lateinit var spinnerCurrency2: Spinner

    private val currencyRates = mapOf(
        "USD" to 1.0,
        "EUR" to 0.85,
        "GBP" to 0.75,
        "INR" to 74.0,
        "JPY" to 110.0
    )

    private var selectedCurrency1 = "USD"
    private var selectedCurrency2 = "USD"

    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextAmount1 = findViewById(R.id.editTextAmount1)
        spinnerCurrency1 = findViewById(R.id.spinnerCurrency1)
        editTextAmount2 = findViewById(R.id.editTextAmount2)
        spinnerCurrency2 = findViewById(R.id.spinnerCurrency2)

        // Set up currency spinners
        val currencies = currencyRates.keys.toList()
        val currencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency1.adapter = currencyAdapter
        spinnerCurrency2.adapter = currencyAdapter

        spinnerCurrency1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCurrency1 = parent.getItemAtPosition(position).toString()
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerCurrency2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCurrency2 = parent.getItemAtPosition(position).toString()
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Add text watchers
        editTextAmount1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isUpdating) {
                    isUpdating = true
                    convertCurrency(fromEditText1 = true)
                    isUpdating = false
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        editTextAmount2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isUpdating) {
                    isUpdating = true
                    convertCurrency(fromEditText1 = false)
                    isUpdating = false
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Handle incoming data from MyApp List
        handleIncomingData()
    }

    private fun handleIncomingData() {
        val data = intent.getStringExtra("data_key")
        if (data != null) {
            Toast.makeText(this, "Received data: $data", Toast.LENGTH_LONG).show()
            // Use the data if needed for any specific logic
        }
    }

    private fun convertCurrency(fromEditText1: Boolean = true) {
        if (fromEditText1) {
            val amount1 = editTextAmount1.text.toString().toDoubleOrNull()
            if (amount1 != null) {
                val rate1 = currencyRates[selectedCurrency1]
                val rate2 = currencyRates[selectedCurrency2]
                if (rate1 != null && rate2 != null) {
                    val convertedAmount = amount1 * (rate2 / rate1)
                    editTextAmount2.setText(convertedAmount.toString())
                } else {
                    Toast.makeText(this, "Invalid currency rates", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            val amount2 = editTextAmount2.text.toString().toDoubleOrNull()
            if (amount2 != null) {
                val rate1 = currencyRates[selectedCurrency1]
                val rate2 = currencyRates[selectedCurrency2]
                if (rate1 != null && rate2 != null) {
                    val convertedAmount = amount2 * (rate1 / rate2)
                    editTextAmount1.setText(convertedAmount.toString())
                } else {
                    Toast.makeText(this, "Invalid currency rates", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
