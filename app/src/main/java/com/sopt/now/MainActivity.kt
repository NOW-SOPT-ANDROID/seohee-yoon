package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.LoginActivity.Companion.ID
import com.sopt.now.LoginActivity.Companion.NICKNAME
import com.sopt.now.LoginActivity.Companion.PW
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        intent.apply {
            binding.tvMainNickname.text = getStringExtra(NICKNAME)
            binding.tvMainId.text = getStringExtra(ID)
            binding.tvMainPw.text = getStringExtra(PW)
        }
    }
}