package com.sopt.now.ui.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserData()
        initSignupBtnClickListener()
    }

    private fun getUserData() {
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

    private fun initLoginBtnClickListener(user: User) {
        binding.btnLoginToSignin.setOnClickListener {
            if (user.id == binding.etLoginId.text.toString() && user.password == binding.etLoginPw.text.toString()) {
                val intent = Intent(this, MainActivity::class.java)

                Toast.makeText(this, R.string.msg_login_success, Toast.LENGTH_SHORT).show()
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, R.string.msg_login_fail, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
