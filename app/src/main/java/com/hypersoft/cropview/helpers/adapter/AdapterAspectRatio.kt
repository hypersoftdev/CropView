package com.hypersoft.cropview.helpers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hypersoft.crop.enums.AspectRatioType
import com.hypersoft.cropview.R
import com.hypersoft.cropview.databinding.ItemAspectRatioBinding
import com.hypersoft.cropview.helpers.models.AspectRatio

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/27/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

class AdapterAspectRatio(private val itemClick: (aspectRatioType: AspectRatioType) -> Unit) : ListAdapter<AspectRatio, AdapterAspectRatio.CustomViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAspectRatioBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = getItem(position)

        bindViews(holder.binding, currentItem)

        holder.binding.root.setOnClickListener { itemClick.invoke(currentItem.aspectRatioType) }
    }

    private fun bindViews(binding: ItemAspectRatioBinding, currentItem: AspectRatio) {
        with(binding) {
            val color = ContextCompat.getColor(root.context, R.color.colorSelection)
            mtvTitleItemAspectRatio.text = currentItem.text
            sivImageItemAspectRatio.setImageResource(currentItem.iconResId)
            when (currentItem.isSelected) {
                true -> root.setCardBackgroundColor(color)
                false -> root.setCardBackgroundColor(0)
            }
        }
    }

    inner class CustomViewHolder(val binding: ItemAspectRatioBinding) : RecyclerView.ViewHolder(binding.root)

    object DiffCallback : DiffUtil.ItemCallback<AspectRatio>() {
        override fun areItemsTheSame(oldItem: AspectRatio, newItem: AspectRatio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AspectRatio, newItem: AspectRatio): Boolean {
            return oldItem == newItem
        }
    }
}