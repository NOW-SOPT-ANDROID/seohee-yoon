package com.sopt.now.ui.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Key.USER_ID
import com.sopt.now.data.ServicePool
import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.response.ResponseLoginDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signup.SignUpActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

class LoginViewModel() : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    val liveData = MutableLiveData<LoginState>()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    val userId = response.headers()["location"]

                    liveData.value = LoginState(
                        isSuccess = true,
                        message = "$userId 님 환영합니다!",
                        userId = userId
                    )
                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error message"
                    val errorMessage = JSONObject(errorBody).getString("message")

                    liveData.value = LoginState(
                        isSuccess = false,
                        message = errorMessage,
                        userId = null
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버에러",
                    userId = null
                )
            }
        })
    }
}