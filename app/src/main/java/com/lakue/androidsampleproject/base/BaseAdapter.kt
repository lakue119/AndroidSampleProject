package com.lakue.androidsampleproject.base

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dn.neighborhoodchores.utils.provider.DefaultResourcesProvider
import com.dn.neighborhoodchores.utils.provider.ResourcesProvider
import com.lakue.androidsampleproject.listener.AdapterListener
import com.lakue.androidsampleproject.remote.model.base.CellType
import com.lakue.androidsampleproject.remote.model.base.Model
import com.lakue.androidsampleproject.utils.BaseUtils.context
import com.lakue.androidsampleproject.utils.mapper.ModelViewHolderMapper

class BaseAdapter<M : Model, VM: BaseViewModel>(
    private var modelList: List<Model>,
    private var viewModel: VM,
    private val resourcesProvider: ResourcesProvider = DefaultResourcesProvider(context),
    private val adapterListener: AdapterListener
) : ListAdapter<Model, BaseViewHolder<M>>(Model.DIFF_CALLBACK) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int{
        return modelList[position].getType1().ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], viewModel, resourcesProvider)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int) {
        Log.i("WQELKRJWKELRJKL", "$position / ${modelList.size} : ${modelList[position]} ")
        with(holder) {
            bindData(modelList[position] as M)
            bindViews(modelList[position] as M, adapterListener)
        }
    }

    override fun submitList(list: List<Model>?) {
        list?.let { modelList = it }
        super.submitList(list)
    }
}
