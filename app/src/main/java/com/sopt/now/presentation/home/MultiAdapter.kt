package com.sopt.now.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Friend
import com.sopt.now.data.model.response.ResponseUserDto
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.databinding.ItemFriendBinding


class MultiAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var friendList: List<Friend> = emptyList()
    private var user = ResponseUserDto.UserData("", "", "")

    companion object {
        const val VIEW_TYPE_USER = 0
        const val VIEW_TYPE_FRIEND = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val binding = ItemUserBinding.inflate(inflater, parent, false)
                UserViewHolder(binding)
            }
            VIEW_TYPE_FRIEND -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.onBind(user)
            is FriendViewHolder -> holder.onBind(friendList[position])
        }
    }

    override fun getItemCount() = friendList.size

    override fun getItemViewType(position: Int): Int {
        return if(position == 0)
            VIEW_TYPE_USER
        else
            VIEW_TYPE_FRIEND
    }

    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }

    fun setUser(userData: ResponseUserDto) {
        user = userData.data
        notifyDataSetChanged()
    }
}