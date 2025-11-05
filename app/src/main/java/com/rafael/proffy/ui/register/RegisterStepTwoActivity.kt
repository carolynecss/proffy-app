package com.rafael.proffy.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafael.proffy.databinding.ActivityRegisterStepTwoBinding

class RegisterStepTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.buttonNext.setOnClickListener {
            validarEFinalizar()
        }

        // Remover erro quando o usuário digitar
        binding.textInputEditEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutEmail.error = null
            }
        }

        binding.textInputEditPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textInputLayoutPassword.error = null
            }
        }
    }

    private fun validarEFinalizar() {
        val email = binding.textInputEditEmail.text.toString().trim()
        val senha = binding.textInputEditPassword.text.toString().trim()

        // Recuperar dados da tela anterior
        val nome = intent.getStringExtra("NOME") ?: ""
        val sobrenome = intent.getStringExtra("SOBRENOME") ?: ""

        val emailValido = validarEmail(email)
        val senhaValida = validarSenha(senha)

        if (emailValido && senhaValida) {
            // Todos os campos válidos, finalizar cadastro
            finalizarCadastro(nome, sobrenome, email, senha)
        }
    }

    private fun validarEmail(email: String): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS

        return when {
            email.isEmpty() -> {
                binding.textInputLayoutEmail.error = "E-mail é obrigatório"
                false
            }
            !emailPattern.matcher(email).matches() -> {
                binding.textInputLayoutEmail.error = "Digite um e-mail válido (ex: usuario@dominio.com)"
                false
            }
            else -> {
                binding.textInputLayoutEmail.error = null
                true
            }
        }
    }

    private fun validarSenha(senha: String): Boolean {
        return when {
            senha.isEmpty() -> {
                binding.textInputLayoutPassword.error = "Senha é obrigatória"
                false
            }
            senha.length < 6 -> {
                binding.textInputLayoutPassword.error = "Senha deve ter pelo menos 6 caracteres"
                false
            }
            else -> {
                binding.textInputLayoutPassword.error = null
                true
            }
        }
    }

    private fun finalizarCadastro(nome: String, sobrenome: String, email: String, senha: String) {
        // Aqui você faria a chamada para a API/servidor
        // Por enquanto, vamos apenas mostrar uma mensagem e finalizar

        android.widget.Toast.makeText(
            this,
            "Cadastro realizado com sucesso!",
            android.widget.Toast.LENGTH_LONG
        ).show()

        // Finalizar todas as activities de cadastro e voltar para login
        finishAffinity()
    }
}4