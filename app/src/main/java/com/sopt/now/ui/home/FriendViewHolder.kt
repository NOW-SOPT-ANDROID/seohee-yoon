package com.sopt.now.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friend) {
        binding.run {
            ivItemFriendProfile.setImageResource(friendData.profileImage)
            tvItemFriendName.text = friendData.name
            tvItemFriendPhone.text = friendData.phone
        }
    }
}