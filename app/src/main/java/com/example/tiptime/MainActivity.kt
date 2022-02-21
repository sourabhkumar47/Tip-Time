package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.calculateButton.setOnClickListener{ calculateTip()}
    }

    fun calculateTip() {
        // Get the text from textfield
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        //Get tip percentage..from which the user selected from a RadioGroup of RadioButtons.
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //Check which radio button is selected
        val tipPercentage = when (selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Calculate the tip and round it of
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}