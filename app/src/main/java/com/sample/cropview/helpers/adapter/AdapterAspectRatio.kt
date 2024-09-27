package com.sample.cropview.helpers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.cropview.R
import com.sample.cropview.databinding.ItemAspectRatioBinding
import com.sample.cropview.helpers.models.AspectRatio
import dev.pegasus.crop.aspectRatio.model.AspectRatioType

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
        with(binding.root) {
            text = currentItem.text
            setCompoundDrawablesWithIntrinsicBounds(0, currentItem.iconResId, 0, 0)
            background = when (currentItem.isSelected) {
                true -> ContextCompat.getColor(context, R.color.colorSelection).toDrawable()
                false -> null
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