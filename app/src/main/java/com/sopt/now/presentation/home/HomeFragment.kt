package com.sopt.now.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.util.KeyStorage.USER_PREF
import com.sopt.now.util.MainApplication

class HomeFragment() : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var multiAdapter: MultiAdapter
    private val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        multiAdapter = MultiAdapter()

        binding.rvHomeFriends.apply {
            adapter = multiAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userData.observe(viewLifecycleOwner) {
            multiAdapter.setUser(it)
        }

        viewModel.friendList.observe(viewLifecycleOwner) {
            multiAdapter.setFriendList(it)
        }

        viewModel.getUserData(MainApplication.prefsManager.getString(USER_PREF, ""))
        viewModel.getFriendData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
