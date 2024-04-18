package com.sopt.now

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemUserBinding

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(user: User) {
        binding.tvItemUserName.text = user.name
        binding.tvItemUserMbti.text = user.mbti
    }
}