package com.lakue.androidsampleproject.utils.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dn.neighborhoodchores.utils.provider.ResourcesProvider
import com.lakue.androidsampleproject.base.BaseViewHolder
import com.lakue.androidsampleproject.base.BaseViewModel
import com.lakue.androidsampleproject.databinding.ItemPocketBinding
import com.lakue.androidsampleproject.remote.model.base.CellType
import com.lakue.androidsampleproject.remote.model.base.Model
import com.lakue.androidsampleproject.ui.main.MainViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): BaseViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.NORMAL_CELL ->
                MainViewHolder(
                    ItemPocketBinding.inflate(inflater, parent, false),
                    viewModel,
                    resourcesProvider
                )

            else -> {}
        }

        return viewHolder as BaseViewHolder<M>
    }

}
