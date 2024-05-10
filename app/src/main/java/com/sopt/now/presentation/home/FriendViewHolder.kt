package com.sopt.now.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.databinding.ItemFriendBinding
import com.bumptech.glide.Glide

class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friend) {
        Glide.with(binding.root)
            .load(friendData.profileImage)
            .into(binding.ivItemFriendProfile)

        binding.run {
            tvItemFriendName.text = friendData.name
            tvItemFriendPhone.text = friendData.phone
        }
    }
}