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

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    private lateinit var friendAdapter: FriendAdapter

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

        friendAdapter = FriendAdapter()
        binding.rvHomeFriends.apply {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        loadFriendList()
    }

    private fun loadFriendList() {
        val jsonString = getJsonDataFromAsset()
        val gson = Gson()
        val friendType = object : TypeToken<List<Friend>>() {}.type

        jsonString?.let {
            val friends: List<Friend> = gson.fromJson(it, friendType)
            friendAdapter.setFriendList(friends)
        }
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