package com.hanifkf.daggerretrofitrxjava.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonParams(
    @Expose
    @SerializedName("first_name")
    val firstName: String,
    @Expose
    @SerializedName("last_name")
    val lastName: String
)