package com.sopt.now.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.data.User
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.ui.login.LoginActivity


class MyPageFragment(private val user: User) : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initLogoutBtnClickListener()
    }

    private fun initViews() {
        binding.apply {
            tvMypageId.text = user.id
            tvMypagePw.text = user.password
            tvMypagePhone.text = user.phone
        }
    }

    private fun initLogoutBtnClickListener() {
        binding.btnMypageLogout.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}