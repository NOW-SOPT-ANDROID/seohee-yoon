package com.sopt.now

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSharedPreferences()
        initViews()
        initLogoutBtnClickListener()
    }

    private fun initSharedPreferences() {
        sharedPreferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    private fun initViews() {
        binding.apply {
            val userId = sharedPreferences.getString(LoginActivity.ID, "")
            val userPw = sharedPreferences.getString(LoginActivity.PW, "")
            val userMbti = sharedPreferences.getString(LoginActivity.MBTI, "")

            tvMypageId.text = userId
            tvMypagePw.text = userPw
            tvMypageMbti.text = userMbti
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.btnMypageLogout.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val editor = sharedPreferences.edit()
        val intent = Intent(requireContext(), LoginActivity::class.java)

        editor.clear()
        editor.apply()
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}