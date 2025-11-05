package com.rafael.proffy.ui.forgot

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Definir as cores do botão para os estados
        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        val buttonGoBack = binding.buttonGoBack
        val editTextEmail = binding.textInputEditEmail
        val buttonSendEmail = binding.buttonSendEmail

        buttonGoBack.setOnClickListener {
            goBack()
        }

        editTextEmail.addTextChangedListener { text ->
            val email = text?.toString()?.trim() ?: ""
            val isValidEmail = isValidEmail(email)

            setButtonState(isValidEmail, enabledButtonColor, disabledButtonColor)
        }
    }

    private fun goBack() {
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        val buttonSendEmail = binding.buttonSendEmail
        buttonSendEmail.isEnabled = enabled

        val color = if (enabled) enabledColor else disabledColor
        buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)

        val textColor = if (enabled) {
            ContextCompat.getColor(this, R.color.shape_01_white)
        } else {
            ContextCompat.getColor(this, R.color.text_complement)
        }

        buttonSendEmail.setTextColor(textColor)
    }
}












