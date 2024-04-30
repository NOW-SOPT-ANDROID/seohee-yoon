package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSignupBtnClickListener()
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            val userID = binding.etSignupId.text.toString()
            val userPw = binding.etSignupPw.text.toString()
            val userNickname = binding.etSignupNickname.text.toString()
            val userMbti = binding.etSignupMbti.text.toString()

            user = User(userID, userPw, userNickname, userMbti)

            checkInput(user)
        }
    }

    private fun checkInput(user: User) {
        return when {
            user.id.length !in 6..10 -> showMessage("아이디는 6자 이상 10자 이하여야 합니다")
            user.password.length !in 8..12 -> showMessage("비밀번호는 8자 이상 12자 이하여야 합니다")
            user.name.isNullOrBlank() -> showMessage("닉네임을 입력해주세요")
            user.mbti.isNullOrBlank() -> showMessage("MBTI를 입력해주세요")
            else -> checkedSignup(user)
        }
    }

    private fun checkedSignup(user: User) {
        Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("user", user)

        startActivity(intent)
    }

    private fun showMessage(message: String)
    {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
