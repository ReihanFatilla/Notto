package com.reihan.notto.helper

import androidx.recyclerview.widget.DiffUtil
import com.reihan.notto.data.local.Notto

class NottoDiffUtil(private val oldList: List<Notto>, private val newList: List<Notto>)
    :DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val OldList = oldList[oldItemPosition]
        val NewList = newList[newItemPosition]
        return OldList.id == NewList.id
                && OldList.date == NewList.date
                && OldList.date == NewList.date
    }
}