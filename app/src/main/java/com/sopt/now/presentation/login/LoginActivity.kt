package com.sopt.now.ui.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.data.KeyStorage.USER_ID
import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>()
    private var userId: String = ""

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
            viewModel.login(getLoginRequestDto())
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { state ->
            Toast.makeText(
                this@LoginActivity,
                state.message,
                Toast.LENGTH_SHORT,
            ).show()

            val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                userId = state.userId ?: ""
                putExtra(USER_ID, userId)
            }
            startActivity(intent)
        }
    }

    private fun getLoginRequestDto(): RequestLoginDto {
        val id = binding.etLoginId.text.toString()
        val password = binding.etLoginPw.text.toString()

        return RequestLoginDto(
            authenticationId = id,
            password = password
        )
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}