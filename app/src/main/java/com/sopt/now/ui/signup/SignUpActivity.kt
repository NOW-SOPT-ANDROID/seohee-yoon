package com.sopt.now.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.Key.USER_PROFILE
import com.sopt.now.data.ServicePool.authService
import com.sopt.now.data.User
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.ResponseSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var id: String
    private lateinit var password: String
    private lateinit var nickname: String
    private lateinit var phone: String

    private val viewModel by viewModels<SignUpViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
    }

    private fun initView() {
        binding.btnSignupToSignup.setOnClickListener {
            viewModel.signUp(getSignUpRequestDto())
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
                goToLogin()
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        id = binding.etSignupId.text.toString()
        password = binding.etSignupPw.text.toString()
        nickname = binding.etSignupNickname.text.toString()
        phone = binding.etSignupPhone.text.toString()

        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phone
        )
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
