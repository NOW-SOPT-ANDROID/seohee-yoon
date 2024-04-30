package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sopt.now.databinding.FragmentHomeBinding
import java.io.IOException

class HomeFragment(private val user: User) : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var friendAdapter: MultiAdapter

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
        val jsonString = getJsonDataFromAsset()
        val user = getUserData()

        val gson = Gson()
        val friendType = object : TypeToken<List<Friend>>() {}.type

        val friendList: List<Friend> = gson.fromJson(jsonString, friendType)

        val userList = mutableListOf<Any>()
        if (user != null) {
            userList.add(user)
        }

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