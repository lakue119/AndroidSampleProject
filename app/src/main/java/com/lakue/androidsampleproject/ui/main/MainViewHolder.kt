package com.lakue.androidsampleproject.ui.main

import com.dn.neighborhoodchores.utils.provider.ResourcesProvider
import com.lakue.androidsampleproject.base.BaseViewHolder
import com.lakue.androidsampleproject.base.BaseViewModel
import com.lakue.androidsampleproject.databinding.ItemPocketBinding
import com.lakue.androidsampleproject.listener.AdapterListener
import com.lakue.androidsampleproject.remote.model.ResultPocket

class MainViewHolder (
    private val binding: ItemPocketBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): BaseViewHolder<ResultPocket>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        tvTitle.text = ""
//        restaurantImage.clear()
    }

    override fun bindData(model: ResultPocket) {
        super.bindData(model)
        with(binding) {
            item = model
        }
    }

    override fun bindViews(model: ResultPocket, adapterListener: AdapterListener) = with(binding) {
        if (adapterListener is PocketListListener) {
            root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

}
