package com.example.a10_fragments

interface ItemSelectListener {
    fun onItemSelected(text: String)
    fun loadDetailFragment(text: String)
    fun loadListFragment ()
}