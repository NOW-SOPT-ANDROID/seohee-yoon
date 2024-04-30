package com.sopt.now


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResultSignup()
        initSignupBtnClickListener()
    }

    private fun getResultSignup() {
        val user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            this.user = user
            initLoginBtnClickListener(user)
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun initLoginBtnClickListener(user:User) {
        binding.btnLoginToSignin.setOnClickListener {
            if (user.id == binding.etLoginId.text.toString() && user.password == binding.etLoginPw.text.toString()) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디 또는 비밀번호가 일치하지 않습니다",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
