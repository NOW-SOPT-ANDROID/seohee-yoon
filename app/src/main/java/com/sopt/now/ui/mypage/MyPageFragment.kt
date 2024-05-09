package com.sopt.now.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sopt.now.data.KeyStorage.USER_ID
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.ui.login.LoginActivity


class MyPageFragment() : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private val viewModel by viewModels<MyPageViewModel>()


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
        val userId = activity?.intent?.getStringExtra(USER_ID)

        if (userId != null) {
            viewModel.getUserData(userId)

            binding.apply {
                viewModel.userData.observe(viewLifecycleOwner) { userData ->
                    if (userData != null) {
                        tvMypageId.text = userData.authenticationId
                        tvMypageNickname.text = userData.nickname
                        tvMypagePhone.text = userData.phone
                    } else {
                        Log.d("MyPageFragment", "User data is null.")
                    }
                }
            }
        } else {
            Log.d("MyPageFragment", "User ID is null.")
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