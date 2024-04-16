package com.sopt.now

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.LoginActivity.Companion.ID
import com.sopt.now.LoginActivity.Companion.MBTI
import com.sopt.now.LoginActivity.Companion.NICKNAME
import com.sopt.now.LoginActivity.Companion.PW
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

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

            checkInput(userID, userPw, userNickname, userMbti)
        }
    }

    private fun checkInput(userID: String, userPw: String, userNickname: String, userMbti: String) {
        return when {
            userID.length < 6 || userID.length > 10 -> showMessage("아이디는 6자 이상 10자 이하여야 합니다")
            userPw.length < 8 || userPw.length > 12 -> showMessage("비밀번호는 8자 이상 12자 이하여야 합니다")
            userNickname.isNullOrBlank() -> showMessage("닉네임을 입력해주세요")
            userMbti.isNullOrBlank() -> showMessage("MBTI를 입력해주세요")
            else -> checkedSignup()
        }
    }

    private fun checkedSignup() {
        val sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()

        Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()

        edit.apply {
            putString(ID, binding.etSignupId.text.toString())
            putString(PW, binding.etSignupPw.text.toString())
            putString(NICKNAME, binding.etSignupNickname.text.toString())
            putString(MBTI, binding.etSignupMbti.text.toString())

            apply()
        }

        val intent = Intent(this, LoginActivity::class.java)
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
