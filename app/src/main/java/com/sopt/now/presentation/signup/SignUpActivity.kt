package com.sopt.now.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.data.model.request.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

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
                navigateToLogin()
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        return RequestSignUpDto(
            authenticationId = binding.etSignupId.text.toString(),
            password = binding.etSignupPw.text.toString(),
            nickname = binding.etSignupNickname.text.toString(),
            phone = binding.etSignupPhone.text.toString()
        )
    }

    private fun navigateToLogin() {
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
