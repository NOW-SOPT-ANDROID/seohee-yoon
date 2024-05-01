package com.sopt.now.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.home.HomeFragment
import com.sopt.now.ui.mypage.MyPageFragment
import com.sopt.now.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<User>("user")!!

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvMain.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvMain.id, HomeFragment(user))
                .commit()
        }

        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment(user))
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment(user))
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction() // Activity에서 Fragment를 다루기 때문
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}