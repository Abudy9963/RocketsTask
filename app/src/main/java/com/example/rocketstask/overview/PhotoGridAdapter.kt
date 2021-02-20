package com.example.rocketstask.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketstask.model.Rocket
import com.example.rocketstask.databinding.GridViewItemBinding
import kotlinx.android.synthetic.main.grid_view_item.view.*

class PhotoGridAdapter(private val listener: Listener)
    :RecyclerView.Adapter<PhotoGridAdapter.RocketViewHolder>(){




    private val differCallBack= object : DiffUtil.ItemCallback<Rocket>(){
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem==newItem

        }

    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : RocketViewHolder {
        return RocketViewHolder(GridViewItemBinding
            .inflate(LayoutInflater.from(parent.context),parent ,false))
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = differ.currentList[position]
        holder.bind(rocket)
        holder.itemView.setOnClickListener {
            listener.onClick(rocket)

        }
        holder.itemView.wikipediaBtn.setOnClickListener {
            listener.onButtonClick(rocket)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class RocketViewHolder(private var binding: GridViewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(rocket: Rocket) {
            binding.rocket = rocket

        }
    }
    interface Listener {
        fun onClick(rocket: Rocket)
        fun onButtonClick(rocket: Rocket)
    }
}