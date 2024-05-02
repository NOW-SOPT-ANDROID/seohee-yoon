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
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<User>("user")
        if (user == null){
            finish()
            return
        }

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvMain.id)
        if (currentFragment == null) {
            user?.let {
                supportFragmentManager.beginTransaction()
                    .add(binding.fcvMain.id, HomeFragment(it))
                    .commit()
            }
        }

        clickBottomNavigation()
    }


    private fun clickBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            val fragment = when(menuItem.itemId) {
                R.id.menu_home -> user?.let { HomeFragment(it) }
                R.id.menu_search -> SearchFragment()
                R.id.menu_mypage -> user?.let { MyPageFragment(it) }
                else -> null
            }

            fragment?.let {
                replaceFragment(it)
                true
            } ?: false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction() // Activity에서 Fragment를 다루기 때문
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}