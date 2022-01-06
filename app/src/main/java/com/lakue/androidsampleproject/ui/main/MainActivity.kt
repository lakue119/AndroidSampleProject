package com.lakue.androidsampleproject.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.dn.neighborhoodchores.utils.provider.DefaultResourcesProvider
import com.lakue.androidsampleproject.R
import com.lakue.androidsampleproject.base.BaseActivity
import com.lakue.androidsampleproject.databinding.ActivityMainBinding
import com.lakue.androidsampleproject.extension.showToast
import com.lakue.androidsampleproject.remote.model.ResponsePocket
import com.lakue.androidsampleproject.remote.model.ResultPocket
import com.lakue.androidsampleproject.utils.WrapContentLinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private var rvloading = false
    private var keyword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObserver()
        viewModel.fetchPocketList(offset = 0)
        settingRecycerView()
    }

    fun settingRecycerView(){
        val animator: ItemAnimator = binding.rvPocket.itemAnimator!!
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        binding.rvPocket.layoutManager = WrapContentLinearLayoutManager(this)
    }

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

        if (!rvloading && lastPositionCount >= adapter?.itemCount!! - 2) {
            rvloading = true
            viewModel.fetchPocketList(offset = adapter.itemCount)
        }
    }

    fun setObserver() {
        viewModel.apply {
            liveSuccess.observe(this@MainActivity) {
                val data = it as ResponsePocket
                this.setPocketData(data.results)
                rvloading = false
            }
        }
    }

}