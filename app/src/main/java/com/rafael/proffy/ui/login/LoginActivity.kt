package com.rafael.proffy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityLoginBinding
import com.rafael.proffy.ui.forgot.ForgotActivity
import com.rafael.proffy.ui.register.RegisterStepOneActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()
        window.statusBarColor = this.getColor(R.color.purple)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonForgot = binding.buttonForgot
        val buttonRegister = binding.buttonSignup

        buttonForgot.setOnClickListener {
            handleForgot()
        }

        buttonRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleForgot() {
        val intent = Intent(this, ForgotActivity::class.java)
        startActivity(intent)
    }

    private fun handleRegister() {
        val intent = Intent(this, RegisterStepOneActivity::class.java)
        startActivity(intent)
    }
}












