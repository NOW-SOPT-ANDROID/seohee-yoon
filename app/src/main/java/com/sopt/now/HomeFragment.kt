package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) // null이 아닌 경우에만 _binding을 반환하여 사용

    private val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "이의경",
            mbti = "ENFP"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "공세영",
            mbti = "INTJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "김명석",
            mbti = "ENFP"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "김아린",
            mbti = "INTJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "김언지",
            mbti = "ISTJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "김윤서",
            mbti = "ESFJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "박효빈",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "배차은우",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "송혜음",
            mbti = "ISFJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "신민석",
            mbti = "ISFP"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "이석찬",
            mbti = "ISFJ"
        ),
        Friend(
            profileImage = R.drawable.img_main_profile,
            name = "이유빈",
            mbti = "ENFP"
        ),
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter()
        binding.rvHomeFriends.run {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}