package com.lakue.androidsampleproject.remote.model

import com.google.gson.annotations.Expose
import com.lakue.androidsampleproject.remote.model.base.CellType
import com.lakue.androidsampleproject.remote.model.base.Model

data class ResultPocket(
    override var id: Long,
    override var type: CellType = CellType.NORMAL_CELL,
    val name: String,
    val url: String
): Model(){

    override fun getType1(): CellType {
        return type ?: CellType.NORMAL_CELL
    }

}
