package com.lakue.androidsampleproject.ui.main

import com.lakue.androidsampleproject.listener.AdapterListener
import com.lakue.androidsampleproject.remote.model.ResponsePocket
import com.lakue.androidsampleproject.remote.model.ResultPocket


interface PocketListListener: AdapterListener {
    fun onClickItem(model: ResultPocket)
}
