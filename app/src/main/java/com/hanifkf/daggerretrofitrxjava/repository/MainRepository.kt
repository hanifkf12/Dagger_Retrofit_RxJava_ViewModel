package com.hanifkf.daggerretrofitrxjava.repository

import com.hanifkf.daggerretrofitrxjava.model.CreatePerson.CreateResponse
import com.hanifkf.daggerretrofitrxjava.model.PersonParams
import com.hanifkf.daggerretrofitrxjava.model.Persons
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import com.hanifkf.daggerretrofitrxjava.network.ApiObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository(private val apiInterface: ApiInterface){
    private val compositeDisposable = CompositeDisposable()
    fun getPersons(onResult:(Persons)->Unit, onError : (Throwable) -> Unit){
        apiInterface.getPersons().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<Persons>(compositeDisposable){
                override fun onApiSuccess(data: Persons) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }

            })
    }

    fun createPerson(params: PersonParams, onResult:(CreateResponse)->Unit, onError : (Throwable) -> Unit){
//        apiInterface.createPersons(params.firstName,params.lastName).observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<CreateResponse>{
//                override fun onComplete() {
//
//                }
//
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(t: CreateResponse) {
//                    onResult(t)
//
//                }
//
//                override fun onError(e: Throwable) {
//                    onError(e)
//                }
//
//            })
        apiInterface.createPersons(params.firstName,params.lastName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<CreateResponse>(compositeDisposable){
                override fun onApiSuccess(data: CreateResponse) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }

            })
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}