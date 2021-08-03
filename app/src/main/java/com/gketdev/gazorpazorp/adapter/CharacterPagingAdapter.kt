package com.gketdev.gazorpazorp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.databinding.ItemCharacterBinding

class CharacterPagingAdapter :
    PagingDataAdapter<Character, CharacterPagingAdapter.ViewHolder>(PhotoComparator) {

    lateinit var onImageClicked: ((Character) -> Unit)

    companion object PhotoComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.characterId == newItem.characterId

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterByPositioned = getItem(position)
        characterByPositioned?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            Glide.with(binding.root.context).load(item.image)
                .into(binding.imageViewCharacter)

            binding.imageViewCharacter.setOnClickListener {
                onImageClicked.invoke(item)
            }
        }

    }
}