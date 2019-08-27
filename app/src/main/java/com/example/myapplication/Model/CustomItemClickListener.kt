package com.example.myapplication.Model

interface CustomItemClickListener {
    fun onItemClicked(position: Int,fname: String)

    fun onItemClickedView(position: Int)
}