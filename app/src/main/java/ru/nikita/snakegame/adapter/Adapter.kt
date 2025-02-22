package ru.nikita.snakegame.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nikita.snakegame.settings.ItemSettingList
import ru.nikita.snakegame.databinding.ItemRecyclerViewBinding

class Adapter(
    private val resources: Resources,
    private val onInteractionListener: OnInteractionListener
) :
    ListAdapter<ItemSettingList, SettingsViewHolder>(SettingsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding, onInteractionListener, resources)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class SettingsViewHolder(
    private val binding: ItemRecyclerViewBinding,
    private val onInteractionListener: OnInteractionListener,
    private val resources: Resources
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemSettingList) {
        binding.itemSettingsIcon.setImageResource(item.icon)
        binding.itemSettingsTitle.text = resources.getText(item.text)
        binding.itemSettingsCheck.isChecked = item.check

        if (item.check)
            binding.itemSettingsCheck.isChecked = true
        else
            binding.itemSettingsCheck.isChecked = false

        itemListener(item)
    }

    private fun itemListener(item: ItemSettingList) {
        binding.itemSettingsCheck.setOnClickListener {
            onInteractionListener.onButtonClicked(item)
        }
    }
}

class SettingsDiffCallback : DiffUtil.ItemCallback<ItemSettingList>() {
    override fun areItemsTheSame(oldItem: ItemSettingList, newItem: ItemSettingList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemSettingList, newItem: ItemSettingList): Boolean {
        return oldItem == newItem
    }
}

interface OnInteractionListener {
    fun onButtonClicked(item: ItemSettingList) {}
}