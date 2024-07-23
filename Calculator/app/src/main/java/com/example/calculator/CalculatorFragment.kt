package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {
    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        editTextNumber1 = view.findViewById(R.id.editTextNumber1)
        editTextNumber2 = view.findViewById(R.id.editTextNumber2)
        textViewResult = view.findViewById(R.id.textViewResult)
        buttonAdd = view.findViewById(R.id.buttonAdd)
        buttonSubtract = view.findViewById(R.id.buttonSubtract)
        buttonMultiply = view.findViewById(R.id.buttonMultiply)
        buttonDivide = view.findViewById(R.id.buttonDivide)

        buttonAdd.setOnClickListener { calculate('+') }
        buttonSubtract.setOnClickListener { calculate('-') }
        buttonMultiply.setOnClickListener { calculate('*') }
        buttonDivide.setOnClickListener { calculate('/') }

        return view
    }

    private fun calculate(operation: Char) {
        val number1 = editTextNumber1.text.toString().toDoubleOrNull()
        val number2 = editTextNumber2.text.toString().toDoubleOrNull()

        if (number1 != null && number2 != null) {
            val result = when (operation) {
                '+' -> number1 + number2
                '-' -> number1 - number2
                '*' -> number1 * number2
                '/' -> {
                    if (number2 != 0.0) {
                        number1 / number2
                    } else {
                        textViewResult.text = "Cannot divide by zero"
                        Toast.makeText(context, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                else -> 0.0
            }
            textViewResult.text = result.toString()
        } else {
            textViewResult.text = "Invalid input"
            Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show()
        }
    }
}
