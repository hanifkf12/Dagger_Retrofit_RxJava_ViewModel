package com.hanifkf.daggerretrofitrxjava.model


import com.google.gson.annotations.SerializedName

data class Persons(
    @SerializedName("count")
    val count: Int,
    @SerializedName("result")
    val result: MutableList<Result>
)