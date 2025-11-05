package com.rafael.proffy.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafael.proffy.databinding.ActivityRegisterStepOneBinding

class RegisterStepOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.buttonNext.setOnClickListener {
            validarEAvancar()
        }

        // Remover erro quando o usuário digitar
        binding.textInputEditFirstName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutFirstName.error = null
            }
        }

        binding.textInputEditLastName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutLastName.error = null
            }
        }
    }

    private fun validarEAvancar() {
        val nome = binding.textInputEditFirstName.text.toString().trim()
        val sobrenome = binding.textInputEditLastName.text.toString().trim()

        val nomeValido = validarNome(nome)
        val sobrenomeValido = validarSobrenome(sobrenome)

        if (nomeValido && sobrenomeValido) {
            // Todos os campos válidos, avançar para próxima tela
            val intent = Intent(this, RegisterStepTwoActivity::class.java)
            intent.putExtra("NOME", nome)
            intent.putExtra("SOBRENOME", sobrenome)
            startActivity(intent)
        }
    }

    private fun validarNome(nome: String): Boolean {
        return when {
            nome.isEmpty() -> {
                binding.textInputLayoutFirstName.error = "Nome é obrigatório"
                false
            }
            nome.length < 4 -> {
                binding.textInputLayoutFirstName.error = "Nome deve ter pelo menos 4 caracteres"
                false
            }
            else -> {
                binding.textInputLayoutFirstName.error = null
                true
            }
        }
    }

    private fun validarSobrenome(sobrenome: String): Boolean {
        return when {
            sobrenome.isEmpty() -> {
                binding.textInputLayoutLastName.error = "Sobrenome é obrigatório"
                false
            }
            sobrenome.length < 4 -> {
                binding.textInputLayoutLastName.error = "Sobrenome deve ter pelo menos 4 caracteres"
                false
            }
            else -> {
                binding.textInputLayoutLastName.error = null
                true
            }
        }
    }
}