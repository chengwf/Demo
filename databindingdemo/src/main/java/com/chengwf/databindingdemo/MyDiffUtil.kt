package com.chengwf.databindingdemo

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil constructor(
    private val oldList: ArrayList<String>,
    private val newList: ArrayList<String>
) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].compareTo(newList[newItemPosition], false) == 0
    }
}