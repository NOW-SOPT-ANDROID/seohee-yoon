package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val idLength = binding.etSignupId.length()
            val pwLength = binding.etSignupPw.length()
            val nicknameLength = binding.etSignupNickname.length()
            checkInput(idLength, pwLength, nicknameLength)
        }
    }

    private fun checkInput(idLength: Int, pwLength: Int, nicknameLength: Int) {
        if (idLength in 6..10) {
            if (pwLength in 8..12) {
                if (nicknameLength >= 1) {
                    if (!binding.etSignupNickname.text.isNullOrBlank()) {
                        val intent = Intent()
                        intent.putExtra("id", binding.etSignupId.text.toString())
                        intent.putExtra("pw", binding.etSignupPw.text.toString())
                        intent.putExtra("nickname", binding.etSignupNickname.text.toString())
                        intent.putExtra("mbtiEI", binding.rgSignupEi.checkedRadioButtonId)
                        intent.putExtra("mbtiSN", binding.rgSignupSn.checkedRadioButtonId)
                        intent.putExtra("mbtiTF", binding.rgSignupTf.checkedRadioButtonId)
                        intent.putExtra("mbtiJP", binding.rgSignupJp.checkedRadioButtonId)

                        Snackbar.make(
                            binding.root,
                            "회원가입이 완료되었습니다",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        setResult(RESULT_OK, intent)
                        finish()
                    }
                    else {
                        Snackbar.make(
                            binding.root,
                            "공백으로만 닉네임을 입력할 수 없습니다",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "닉네임을 입력해주세요",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "비밀번호는 8자 이상 12자 이하여야 합니다",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        } else {
            Snackbar.make(
                binding.root,
                "아이디는 6자 이상 10자 이하여야 합니다",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
