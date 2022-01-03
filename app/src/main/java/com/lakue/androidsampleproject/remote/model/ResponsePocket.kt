package com.lakue.androidsampleproject.remote.model

data class ResponsePocket(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultPocket>
)