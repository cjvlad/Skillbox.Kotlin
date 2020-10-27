package com.example.a08_activitylifecycle

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dial.*

class DialActivity : AppCompatActivity() {

    companion object {
        private const val DIAL_REQUEST_CODE = 9
    }

    private val dialLauncher = prepareCall(ActivityResultContracts.Dial()) { result ->
        println(result.toString())
        Toast.makeText(this, "Звонок завершён!", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dial)
        callButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val isPhoneNumberValid = Patterns.PHONE.matcher(phoneNumber).matches()
            if (!isPhoneNumberValid) {
                Toast.makeText(this, "Введите корректный формат номера!", Toast.LENGTH_SHORT).show()
            } else {
                dialLauncher.launch(phoneNumber)
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DIAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Звонок завершён!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Не дозвонились!", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}