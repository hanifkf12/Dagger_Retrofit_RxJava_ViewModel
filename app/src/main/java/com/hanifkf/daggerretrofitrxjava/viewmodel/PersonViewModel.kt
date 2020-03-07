package com.hanifkf.daggerretrofitrxjava.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf.daggerretrofitrxjava.MainActivity
import com.hanifkf.daggerretrofitrxjava.MyApplication
import com.hanifkf.daggerretrofitrxjava.model.CreatePerson.CreateResponse
import com.hanifkf.daggerretrofitrxjava.model.CreatePerson.ResultCreate
import com.hanifkf.daggerretrofitrxjava.model.PersonParams
import com.hanifkf.daggerretrofitrxjava.model.Persons
import com.hanifkf.daggerretrofitrxjava.model.Result
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import com.hanifkf.daggerretrofitrxjava.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    var count : MutableLiveData<List<Result>?> = MutableLiveData()
    var status : MutableLiveData<Boolean> = MutableLiveData()

    fun getPersons(){
        status.value = true
        repository.getPersons({
            count.value = it.result
            status.value = false
        },{
            Log.e("ERRR", it.message!!)
        })
    }

    fun createPerson(params: PersonParams){
        status.value = true
        repository.createPerson(params,{
            getPersons()
            status.value = false
        },{
            Log.e("ERRR", it.message!!)
        })
    }



    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}