package com.example.a10_fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(this).load("https://aviado.ru/infratrans-content/pictures/2020/06/airbus-a350-cockpit.jpg").into(imageView)

        Log.v(tag, "OnCreate was called") // VERBOSE
        Log.d(tag, "OnCreate was called") // DEBUG
        Log.i(tag, "OnCreate was called") // INFO
        Log.e(tag, "OnCreate was called") // ERROR
        Log.w(tag, "OnCreate was called") // WARN
        Log.println(Log.ASSERT, tag, "OnCreate was called") // ASSERT

        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!inputEmail.isEmailValid()) inputEmail.error =
                    "Пример ***@gmail.com"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginButton.isEnabled = isAuthValid()
            }
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (inputPassword.text.isBlank()) inputPassword.error = "Должен содержать больше 10 символов"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginButton.isEnabled = isAuthValid()
            }
        })

        checkboxAgreement.setOnCheckedChangeListener { _, _ ->
            loginButton.isEnabled = isAuthValid()
        }

        loginButton.setOnClickListener {
            val x = 10
            if (inputEmail.text.toString().substringAfter('@') != "gmail.com") {
                inputEmail.error = "Пример: ***@gmail.com"
                errorTextView.text = getString(R.string.form_not_true)
                state = FormState(false, getString(R.string.form_not_true))
            }
            if (inputPassword.text.length < x) {
                inputPassword.error = "Должен содержать больше 10 символов"
                errorTextView.text = getString(R.string.form_not_true)
                state = FormState(false, getString(R.string.form_not_true))
            } else {
                errorTextView.text = ""
                state = FormState(true, "")
                makeAction()
            }
        }

    }


    companion object {
        private const val FORM_STATE = "Form state"
    }

    private var state: FormState? = FormState(false, "Ошибка!")

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(FORM_STATE, state)
    }

    private fun EditText.isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
    }

    private fun isAuthValid(): Boolean {
        return inputEmail.isEmailValid() && inputPassword.text.isNotBlank() && checkboxAgreement.isChecked
    }

    private fun makeAction() {
        inputEmail.isEnabled = false
        inputPassword.isEnabled = false
        checkboxAgreement.isEnabled = false
        loginButton.isEnabled = false
        operationProcess.visibility = View.VISIBLE

        Handler().postDelayed({
            inputEmail.text.clear()
            inputEmail.isEnabled = true
            inputPassword.text.clear()
            inputPassword.isEnabled = true
            checkboxAgreement.isEnabled = true
            checkboxAgreement.isChecked = false
            loginButton.isEnabled = true
            operationProcess.visibility = View.GONE
            (activity as MainActivity).showMainFragment()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(tag, "Exit from App ${hashCode()}")
    }
}




