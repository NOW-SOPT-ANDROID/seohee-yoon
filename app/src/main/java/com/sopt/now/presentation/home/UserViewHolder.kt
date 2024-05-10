package com.sopt.now.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.model.response.ResponseUserDto
import com.sopt.now.databinding.ItemUserBinding

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(user: ResponseUserDto.UserData) {
        binding.tvItemUserName.text = user.nickname
        binding.tvItemUserPhone.text = user.phone
    }
}