package com.chalkboard.birthdays.helpers

import androidx.recyclerview.widget.DiffUtil

class DiffUtilsCallback<T>(private val newItems: List<T>, private val oldItems: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition] === newItems[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}
