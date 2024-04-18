package com.sopt.now

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding

class MultiAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<Any> = emptyList()

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
            is UserViewHolder -> holder.onBind(itemList[position] as User)
            is FriendViewHolder -> holder.onBind(itemList[position] as Friend)
        }
    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is User -> VIEW_TYPE_USER
            is Friend -> VIEW_TYPE_FRIEND
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    fun setItemList(itemList: List<Any>) {
        this.itemList = itemList.toList()
        notifyDataSetChanged()
    }
}