package com.lakue.androidsampleproject.ui.main

import com.dn.neighborhoodchores.utils.provider.DefaultResourcesProvider
import com.lakue.androidsampleproject.R
import com.lakue.androidsampleproject.base.BaseActivity
import com.lakue.androidsampleproject.databinding.ActivityMainBinding
import com.lakue.androidsampleproject.extension.showToast
import com.lakue.androidsampleproject.remote.model.ResponsePocket
import com.lakue.androidsampleproject.remote.model.ResultPocket

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private var rvloading = false
    private var keyword = ""

    val adapterListsnser = object : PocketListListener {
        override fun onClickItem(model: ResultPocket) {
            showToast(model.name)
        }
    }

    val resourcesProvider by lazy {
        DefaultResourcesProvider(
            this
        )
    }

    //BottomCatch Event - 마지막 Item개수
    val rvBottomCatch: Function1<Int, Unit> = this::onBottomCatch


    //RecyclerView Bottom Catch
    fun onBottomCatch(lastPositionCount: Int) {
        val adapter = binding.rvPocket.adapter

        if(!rvloading && lastPositionCount >= adapter?.itemCount!! - 2){
            rvloading = true
//            getPheedList()
        }
    }

}