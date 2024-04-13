package com.sopt.now

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
        val intent = Intent()
        intent.putExtra(ID, binding.etSignupId.text.toString())
        intent.putExtra(PW, binding.etSignupPw.text.toString())
        intent.putExtra(NICKNAME, binding.etSignupNickname.text.toString())
        intent.putExtra(MBTI, binding.etSignupMbti.text.toString())

        Toast.makeText(this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()

        setResult(RESULT_OK, intent)
        finish()
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
