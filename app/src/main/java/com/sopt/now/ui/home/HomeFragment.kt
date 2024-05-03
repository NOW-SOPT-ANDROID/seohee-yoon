package com.sopt.now.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.data.Friend
import com.sopt.now.data.Key.USER_ID
import com.sopt.now.data.ServicePool
import com.sopt.now.data.dto.response.ResponseFriendDto
import com.sopt.now.data.dto.response.ResponseUserDto
import com.sopt.now.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment() : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")

    lateinit var multiAdapter: MultiAdapter
    private var userId: String? = null

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
        userId = activity?.intent?.getStringExtra(USER_ID)

        getFriendData()
        getUserData(userId)

        multiAdapter = MultiAdapter()

        binding.rvHomeFriends.apply {
            adapter = multiAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var mockFriendList = mutableListOf<Friend>()
    private fun getFriendData() {
        ServicePool.friendService.getFriend(2).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>
            ) {
                if(response.isSuccessful) {
                    val friend = response.body()?.data

                    friend?.forEach { friendData ->
                        mockFriendList.add(
                            Friend(friendData.avatar, friendData.first_name, friendData.email)
                        )
                    }

                    multiAdapter.setFriendList(mockFriendList)
                } else {
                    Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                Log.d("HomeViewModel", "Load friend data error : ${t.message}")
            }

        })
    }

    private fun getUserData(userId: String?) {
        ServicePool.userService.getUser(userId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    multiAdapter.setUser(data)
                } else {
                    Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.d("HomeViewModel", "Failed to load user data: ${t.message}")
            }
        })
    }
}