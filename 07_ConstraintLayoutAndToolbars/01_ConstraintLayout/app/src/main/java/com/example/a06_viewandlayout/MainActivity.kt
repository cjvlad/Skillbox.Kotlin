package com.example.a06_viewandlayout
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // передается ресурс разметки из activity_main
        Glide.with(this).load("https://sun9-21.userapi.com/c851216/v851216688/c4f91/T9qZhBJNG60.jpg").into(imageView)

        val textWatcher = object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginButton.isEnabled = everythingIsFilled()
            }
        }

        inputPassword.addTextChangedListener(textWatcher)

        checkboxAgreement.setOnClickListener{
            loginButton.isEnabled = everythingIsFilled()
        }

        loginButton.setOnClickListener {
            Toast.makeText(this, "Нажатие кнопки", Toast.LENGTH_LONG).show()
            enterProfile()
        }

        loginButton.setOnClickListener {
            makeOperation()
        }

    }

    private fun everythingIsFilled() = inputEmail.text.isNotEmpty()
            && inputPassword.text.isNotEmpty()
            && checkboxAgreement.isChecked

    private fun enterProfile(){
        inputEmail.isEnabled = false
        inputPassword.isEnabled = false
        checkboxAgreement.isEnabled = false
        Handler().postDelayed({
            inputEmail.isEnabled = true
            inputPassword.isEnabled = true
            checkboxAgreement.isEnabled = true
            Toast.makeText(this, R.string.operation_complete, Toast.LENGTH_LONG).show()
        }, 2000)

    }

    private fun makeOperation() {
        operationProcess.visibility = View.VISIBLE
        loginButton.isEnabled = false
        Handler().postDelayed({
            operationProcess.visibility = View.GONE
            loginButton.isEnabled = false
            inputEmail.isEnabled = false
            inputPassword.isEnabled = false
            checkboxAgreement.isEnabled = false
            Toast.makeText(this, R.string.operation_complete, Toast.LENGTH_SHORT).show()
        }, 2000)
    }
}


