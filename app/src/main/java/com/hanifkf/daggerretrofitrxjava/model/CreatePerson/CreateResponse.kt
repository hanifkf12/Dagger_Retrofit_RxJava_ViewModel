package com.hanifkf.daggerretrofitrxjava.model.CreatePerson


import com.google.gson.annotations.SerializedName

data class CreateResponse(
    @SerializedName("result")
    val result: ResultCreate
)