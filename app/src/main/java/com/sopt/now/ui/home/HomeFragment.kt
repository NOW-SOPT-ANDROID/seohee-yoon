package com.sopt.now.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.data.Friend
import com.sopt.now.data.User
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment(private val user: User) : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var multiAdapter: MultiAdapter
    private val viewModel by viewModels<HomeViewModel>()

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

        combineList()
    }

    private fun loadFriendList(): MutableList<Friend> {
        val friendList: MutableList<Friend> = mutableListOf()
        friendList.addAll(viewModel.mockFriendList)

        return friendList
    }

    private fun combineList() {
        val combinedList = mutableListOf<Any>().apply {
            add(user.copy())
            addAll(loadFriendList())
        }

        multiAdapter.setItemList(combinedList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}