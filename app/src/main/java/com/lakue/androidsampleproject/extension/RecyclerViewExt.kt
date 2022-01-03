package com.lakue.androidsampleproject.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dn.neighborhoodchores.utils.provider.DefaultResourcesProvider
import com.dn.neighborhoodchores.utils.provider.ResourcesProvider
import com.lakue.androidsampleproject.base.BaseAdapter
import com.lakue.androidsampleproject.base.BaseViewModel
import com.lakue.androidsampleproject.listener.AdapterListener
import com.lakue.androidsampleproject.remote.model.base.Model

/**
 * RecyclerView Adapter
 */
@BindingAdapter(value = ["lakue_item", "lakue_viewModel", "lakue_resProvider", "lakue_listsner"])
fun RecyclerView.setBindItem(
    modelList: List<Model>? = null,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider = DefaultResourcesProvider(
        context
    ),
    adapterListener: AdapterListener
) {
    var adapter: BaseAdapter<Model, BaseViewModel>? = null

    setHasFixedSize(true)
    if(this.adapter == null){
        adapter = modelList?.let {
            BaseAdapter(
                it,
                viewModel, resourcesProvider, adapterListener)
        }
        setAdapter(adapter)
    } else {
        modelList?.let{
            (this.adapter as BaseAdapter<*, *>).submitList(it)
            (this.adapter as BaseAdapter<*, *>).notifyDataSetChanged()
        }
    }

}

@BindingAdapter("onBottomCatchEvent")
fun RecyclerView.onBottomCatchEvent(f: Function1<Int, Unit>?) {

    if (f == null) //addOnScrollListener(null)
    else addOnScrollListener(object :RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            f(lastVisibleItemPosition)
        }
    })
}