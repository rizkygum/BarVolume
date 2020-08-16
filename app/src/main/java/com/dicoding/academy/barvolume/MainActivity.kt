package com.dicoding.academy.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate){
            val inputLength = edtLength.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            val inputWidht = edtWidth.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            when{
                inputLength.isEmpty() -> {
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh kosong"
                }
                inputHeight.isEmpty() -> {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }
                inputWidht.isEmpty() -> {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }
            }

            val length = toDouble(inputLength)
            val height = toDouble(inputHeight)
            val width = toDouble(inputWidht)

            when{
                length == null -> {
                    isInvalidDouble = true
                    edtLength.error = "Field ini harus berupa nomor yang valid"
                }
                height == null -> {
                    isInvalidDouble = true
                    edtHeight.error = "Field ini harus berupa nomor yang valid"
                }
                width == null -> {
                    isInvalidDouble = true
                    edtHeight.error = "Field ini harus berupa nomor yang valid"
                }
            }

            if (!isEmptyFields && !isInvalidDouble){
                val volume = length as Double * width as Double  * height as Double
                tvResult.text = volume.toString()
            }

        }
    }

    private fun toDouble(str: String): Double?{
        return try {
            str.toDouble()
        }catch (e: NumberFormatException){
            null
        }
    }
}