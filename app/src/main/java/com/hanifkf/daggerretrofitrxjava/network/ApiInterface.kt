package com.hanifkf.daggerretrofitrxjava.network

import androidx.lifecycle.Observer
import com.hanifkf.daggerretrofitrxjava.model.CreatePerson.CreateResponse
import com.hanifkf.daggerretrofitrxjava.model.CreatePerson.ResultCreate
import com.hanifkf.daggerretrofitrxjava.model.PersonParams
import com.hanifkf.daggerretrofitrxjava.model.Persons
import com.hanifkf.daggerretrofitrxjava.model.Result
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {
    @GET("/persons")
    fun getPersons() : Observable<Persons>

//    @Headers( "Content-Type: application/json" )
    @FormUrlEncoded
    @POST("/person")
    fun createPersons(@Field("first_name") firstName:String, @Field("last_name") lastName:String) : Observable<CreateResponse>
}