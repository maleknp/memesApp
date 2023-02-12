package com.example.apiproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apiproject2.databinding.MemeItemBinding
import com.example.apiproject2.model.Meme

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 07,Feb,2023
 */
class AdapterMemes : ListAdapter<Meme, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meme>() {

            override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem.ups == newItem.ups
            }

            override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
                return oldItem.title == newItem.title || oldItem.ups == newItem.ups ||
                        oldItem.preview == newItem.preview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val  binding: MemeItemBinding =
            MemeItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            val item = getItem(position)
            holder.bind(item)
        }
    }


    inner class ViewHolder(val itemBinding: MemeItemBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Meme){
            itemBinding.apply {
                title.text = item.title
                author.text = item.author
                picture.load(item.preview[2])
            }
        }
    }
}