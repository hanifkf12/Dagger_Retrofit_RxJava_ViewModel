package com.hanifkf.daggerretrofitrxjava.network

import androidx.lifecycle.Observer
import com.hanifkf.daggerretrofitrxjava.model.Persons
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("/persons")
    fun getPersons() : Observable<Persons>
}