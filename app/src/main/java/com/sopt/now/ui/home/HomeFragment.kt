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
import java.io.IOException

class HomeFragment(private val user: User) : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var friendAdapter: MultiAdapter
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

        friendAdapter = MultiAdapter()
        binding.rvHomeFriends.apply {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        loadFriendList()
    }


    private fun getUserData(): User? {
        binding.apply {
            return User(user.id, user.password, user.name, user.phone)
        }
    }

    private fun loadFriendList() {
        val user = getUserData()

        val userList = mutableListOf<Any>()
        if (user != null) {
            userList.add(user)
        }

        val friendList: MutableList<Friend> = mutableListOf()
        friendList.addAll(viewModel.mockFriendList)

        val combinedList: MutableList<Any> = mutableListOf()
        combinedList.addAll(userList)
        combinedList.addAll(friendList)

        friendAdapter.setItemList(combinedList)
    }

    private fun getJsonDataFromAsset(): String? {
        return try {
            requireActivity().assets.open("Friend.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}