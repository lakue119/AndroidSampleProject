package com.lakue.androidsampleproject.remote.model.base

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil

/**
 * Created by leekijung on 2020. 1. 28..
 */

abstract class Model{
    abstract var id: Long
    abstract var type: CellType

    abstract fun getType1(): CellType

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Model> = object : DiffUtil.ItemCallback<Model>() {
            override fun areItemsTheSame(@NonNull oldItem: Model, @NonNull newItem: Model): Boolean {
                return oldItem.id == newItem.id //&& oldItem.type == newItem.type
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(@NonNull oldItem: Model, @NonNull newItem: Model): Boolean {
                return oldItem == newItem
            }
        }
    }
}
