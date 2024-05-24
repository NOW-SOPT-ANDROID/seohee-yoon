package com.sopt.now.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.ViewModelFactory
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.domain.model.AuthData
import com.sopt.now.presentation.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val viewModel by viewModels<SignUpViewModel>{ ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignUpBtnClickListener()
        initObserver()
    }

    private fun initSignUpBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            viewModel.signUp(getAuthData())
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { state ->
            Toast.makeText(
                this@SignUpActivity,
                state.message,
                Toast.LENGTH_SHORT,
            ).show()

            if (state.isSuccess)
                navigateToLogin()
        }
    }

    private fun getAuthData(): AuthData {
        return AuthData(
            id = binding.etSignupId.text.toString(),
            pw = binding.etSignupPw.text.toString(),
            nickname = binding.etSignupNickname.text.toString(),
            phone = binding.etSignupPhone.text.toString()
        )
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
