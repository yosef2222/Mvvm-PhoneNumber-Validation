package com.example.mvvmphonenumbervalidation
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PhoneNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PhoneNumberViewModel::class.java)

        val inputField: EditText = findViewById(R.id.phone_input)
        val button: Button = findViewById(R.id.convert_button)
        val resultField: TextView = findViewById(R.id.result_text)

        button.setOnClickListener {
            val phoneNumber = inputField.text.toString()
            viewModel.formatPhoneNumber(phoneNumber)
        }

        viewModel.formattedPhoneNumber.observe(this, { formattedPhoneNumber ->
            resultField.text = formattedPhoneNumber
        })
    }
}
