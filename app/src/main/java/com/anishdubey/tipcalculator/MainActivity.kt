package com.anishdubey.tipcalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anishdubey.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var billAmount: Double = 0.0
    private var percentTip: Float = 0.0f
    private var totalBill: Double = 0.0
    private var timeNow: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
        binding.textInputEditText.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    private fun calculateTip() {
            // Bill Amount entered by the user
            billAmount = binding.textInputEditText.text.toString().toDoubleOrNull()!!

            if(binding.textInputEditText.text.toString().isNotEmpty()) {
                // Percentage tip calculator
                percentTip = when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.amazing -> 0.2F
                    R.id.good -> 0.15F
                    R.id.ok -> 0.1F
                    else -> 0.01F
                }

                // Tip
                var tip = billAmount * percentTip

                // Calculate total bill with tip percentage
                totalBill = (billAmount + billAmount * percentTip).toDouble()

                // Show only upto 4 decimal places after amount
                totalBill = (totalBill * 10000.0).roundToLong() / 10000.0

                // Round off the number to nearest Integer
                if (binding.switchButton.isChecked) {
                    totalBill = totalBill.roundToInt().toDouble()
                    tip = kotlin.math.ceil(tip)
                }

                // Show the amount to user
                /*
                val local = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0);
                Toast.makeText(this,local.toString(),Toast.LENGTH_SHORT).show()
             */
                val formattedTip = NumberFormat.getCurrencyInstance(Locale("en", "in")).format(tip)
                binding.finalAmount.text = "Tip Amount: $formattedTip"
            }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if(timeNow+2000> System.currentTimeMillis()) {
            super.onBackPressed()
        }
        else{
            Toast.makeText(this,"Press Again to Exit",Toast.LENGTH_SHORT).show()
        }
        timeNow = System.currentTimeMillis()
    }
}