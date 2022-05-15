package com.example.jntest.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jntest.R
import com.example.jntest.databinding.RecyclerLayoutRepositoriesBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoriesRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<RepositoriesRecyclerAdapter.MyViewHolder>() {

    private lateinit var context: Context
    lateinit var callback: IRepositoriesRecyclerCallBack             //it shouldn't be private for dependency injection

    constructor(
        @ActivityContext context: Context,
        callback: IRepositoriesRecyclerCallBack
    ) : this() {
        this.context = context
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerLayoutRepositoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.recycler_layout_repositories, parent, false)
        val view: View = binding.root

        return MyViewHolder(view, binding, context, callback)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.setRowData(position)

        //Annimation
        val animation =
            AnimationUtils.loadAnimation(myViewHolder.itemView.context, android.R.anim.fade_in)
        myViewHolder.itemView.animation = animation
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class MyViewHolder(
        private val view: View,
        private val binding: RecyclerLayoutRepositoriesBinding,
        private val context: Context,
        private val callback: IRepositoriesRecyclerCallBack
    ) : RecyclerView.ViewHolder(view) {

        init {
            binding.repositoriesAdapter = this@MyViewHolder
        }

        fun onRowClick() {
            callback.onRecyclerClick(adapterPosition)
        }

        fun setRowData(position: Int) {

        }
    }

    interface IRepositoriesRecyclerCallBack {
        fun onRecyclerClick(position: Int)
    }
}