package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.databinding.ActivitySignUpBinding
import kotlin.properties.Delegates

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data

                val id = data?.getStringExtra("id")
                val pw = data?.getStringExtra("pw")
                val nickname = data?.getStringExtra("nickname")

                binding.etLoginId.setText(id)
                binding.etLoginPw.setText(pw)

                binding.btnLogin.setOnClickListener{
                    if (id == binding.etLoginId.text.toString() && pw == binding.etLoginPw.text.toString()) {
                        Snackbar.make(
                            binding.root,
                            "로그인 성공",
                            Snackbar.LENGTH_LONG
                        ).show()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("pw", pw)
                        intent.putExtra("nickname", nickname)
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

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}
