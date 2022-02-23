package com.example.baksheeshcalculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.baksheeshcalculate.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hesaplaButton.setOnClickListener{
            bahsisHesapla()
        }
    }


    fun bahsisHesapla(){

        val hizmetBedeliString = binding.hizmetBedeliEditText.text.toString()

        val tutar = hizmetBedeliString.toDoubleOrNull()

        if (tutar == null){
            binding.bahsisTutariTextView.text = ""
            return
        }

        val secilenRadioButton = binding.bahsisSecenekleriRadioGroup.checkedRadioButtonId

        val bahşişYüzdesi = when(secilenRadioButton){

            R.id.yuzde_yirmi_radio_button -> 0.20
            R.id.yuzde_onsekiz_radio_button -> 0.18
            else -> 0.15

        }
        var bahsis = bahşişYüzdesi * tutar

        val yukarıYuvarla = binding.bahsisYukarYuvarlaSwitch.isChecked

        if(yukarıYuvarla){
            bahsis = kotlin.math.ceil(bahsis)

        }



        val formatlananBahsis = NumberFormat.getCurrencyInstance().format(bahsis)
        binding.bahsisTutariTextView.text = getString(R.string.bahsis_tutari, formatlananBahsis)


    }

}