package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var userId:String ?= null
    private var userPw:String ?= null
    private var userNickname:String ?= null
    private var userMbti:String ?= null

    companion object {
        const val ID = "id"
        const val PW = "pw"
        const val NICKNAME = "nickname"
        const val MBTI = "mbti"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResultSignup()
        initSignupBtnClickListener()
        initLoginBtnClickListener()
    }

    private fun getResultSignup() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data

                userId = data?.getStringExtra(ID)
                userPw = data?.getStringExtra(PW)
                userNickname = data?.getStringExtra(NICKNAME)
                userMbti = data?.getStringExtra(MBTI)

                binding.etLoginId.setText(userId)
                binding.etLoginPw.setText(userPw)
            }
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btnSignupToSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginToSignin.setOnClickListener{
            if (userId == binding.etLoginId.text.toString() && userPw == binding.etLoginPw.text.toString()) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)

                intent.apply {
                    putExtra(ID, userId)
                    putExtra(PW, userPw)
                    putExtra(NICKNAME, userNickname)
                    putExtra(MBTI, userMbti)
                }

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
