package com.example.myapplication

interface CustomItemClickListener {
    fun onItemClicked(position: Int,fname: String)

    fun onItemClickedView(position: Int)
}