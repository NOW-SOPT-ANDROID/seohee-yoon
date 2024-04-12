package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            binding.tvMainNickname.text = getStringExtra("nickname")
            binding.tvMainId.text = getStringExtra("id")
            binding.tvMainPw.text = getStringExtra("pw")
        }
    }
}