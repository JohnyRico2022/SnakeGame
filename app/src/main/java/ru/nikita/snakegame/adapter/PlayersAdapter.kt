package ru.nikita.snakegame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nikita.snakegame.databinding.ItemPlayerBinding
import ru.nikita.snakegame.models.Players

class PlayersAdapter : ListAdapter<Players, PlayersViewHolder>(PlayersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class PlayersViewHolder(
    private val binding: ItemPlayerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Players) {
        binding.iconPlayer.text = item.icon
        binding.namePlayer.text = item.name
    }
}


class PlayersDiffCallback : DiffUtil.ItemCallback<Players>() {
    override fun areItemsTheSame(oldItem: Players, newItem: Players): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Players, newItem: Players): Boolean {
        return oldItem == newItem
    }
}