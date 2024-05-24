package com.sopt.now.presentation.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.ViewModelFactory
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.presentation.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel:LoginViewModel by viewModels<LoginViewModel>{ ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupBtnClickListener()
        initView()
        initObserver()
    }

    private fun initView() {
        binding.btnLoginToSignin.setOnClickListener {
            viewModel.login(getLoginData())
        }
    }

    private fun initObserver() {
        viewModel.loginData.observe(this) { state ->
            Toast.makeText(
                this@LoginActivity,
                state.message,
                Toast.LENGTH_SHORT,
            ).show()

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

    private fun getLoginData(): Pair<String, String> {
        val id = binding.etLoginId.text.toString()
        val password = binding.etLoginPw.text.toString()

        return Pair(id, password)
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}