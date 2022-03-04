package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.calculateButton.setOnClickListener{ calculateTip()}
    }

    private fun calculateTip() {
        // Get the text from textfield
        val stringInTextField = binding.costOfServiceEditText.text.toString()

        //added null , if text field is empty app should not crash
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {

            //to update textview when entry is empty
            displayTip(0.0)
            return

        }

        //Get tip percentage..from which the user selected from a RadioGroup of RadioButtons.

        //Check which radio button is selected
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Round of
        //Calculate the tip and round it of
        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        //changing to currency
        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        // Display the formatted tip value on screen
        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
    private fun handleKeyEvent(view: View,keyCode: Int) : Boolean {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            //HIde the Keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return  false
    }
}