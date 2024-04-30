package com.sopt.now

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friend) {
        binding.run {
            tvItemName.text = friendData.name
            tvItemPhone.text = friendData.mbti
        }
    }
}