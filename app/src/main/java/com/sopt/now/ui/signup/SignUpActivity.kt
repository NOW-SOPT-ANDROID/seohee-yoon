package com.sopt.now.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.data.Key.USER_PROFILE
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.login.LoginActivity

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
            val userPhone = binding.etSignupPhone.text.toString()

            user = User(userID, userPw, userNickname, userPhone)

            checkInput(user)
        }
    }

    private fun checkInput(user: User) {
        return when {
            user.id.length !in 6..10 -> showMessage(getString(R.string.msg_id_fail))
            user.password.length !in 8..12 -> showMessage(getString(R.string.msg_password_fail))
            user.name.isNullOrBlank() -> showMessage(getString(R.string.msg_nickname_fail))
            user.phone.isNullOrBlank() -> showMessage(getString(R.string.msg_phone_fail))
            else -> checkedSignup(user)
        }
    }

    private fun checkedSignup(user: User) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(USER_PROFILE, user)

        Toast.makeText(this, R.string.msg_signup_success, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
