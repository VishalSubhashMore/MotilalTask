package com.example.motilaltask.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.motilaltask.R
import com.example.motilaltask.databinding.LayoutRepositoryListItemBinding
import com.example.motilaltask.database.entity.Repository

class RepositoryRecyclerViewAdapter() :
    RecyclerView.Adapter<RepositoryRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var repositoryList: List<Repository>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.layout_repository_list_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerViewItemBinding.repository = repositoryList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(repositoryList: List<Repository>) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    override fun getItemCount() = repositoryList.size

    inner class MyViewHolder(val recyclerViewItemBinding: LayoutRepositoryListItemBinding) :
        RecyclerView.ViewHolder(recyclerViewItemBinding.root)
}