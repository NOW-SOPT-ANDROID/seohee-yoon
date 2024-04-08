package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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
        binding.btnSignup.setOnClickListener {
            val userID = binding.etSignupId.text.toString()
            val userPw = binding.etSignupPw.text.toString()
            val userNickname = binding.etSignupNickname.text.toString()
            checkInput(userID, userPw, userNickname)
        }
    }

    private fun checkInput(userID: String, userPw: String, userNickname: String) {
        return when {
            userID.length < 6 || userID.length > 10 -> showMessageIdLength()
            userPw.length < 8 || userPw.length > 12 -> showMessagePwLength()
            userNickname.isNullOrBlank() -> showMessageNickname()
            else -> checkedSignup()
        }
    }

    private fun checkedSignup() {
        val intent = Intent()
        intent.putExtra("id", binding.etSignupId.text.toString())
        intent.putExtra("pw", binding.etSignupPw.text.toString())
        intent.putExtra("nickname", binding.etSignupNickname.text.toString())

        Snackbar.make(
            binding.root,
            "회원가입이 완료되었습니다",
            Snackbar.LENGTH_LONG
        ).show()

        setResult(RESULT_OK, intent)
        finish()
    }

    private fun showMessageIdLength() {
        Snackbar.make(
            binding.root,
            "아이디는 6자 이상 10자 이하여야 합니다",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showMessagePwLength() {
        Snackbar.make(
            binding.root,
            "비밀번호는 8자 이상 12자 이하여야 합니다",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showMessageNickname() {
        Snackbar.make(
            binding.root,
            "닉네임을 입력해주세요",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
