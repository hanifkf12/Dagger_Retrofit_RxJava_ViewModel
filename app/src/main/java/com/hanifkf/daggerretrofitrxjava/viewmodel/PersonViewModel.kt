package com.hanifkf.daggerretrofitrxjava.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf.daggerretrofitrxjava.MainActivity
import com.hanifkf.daggerretrofitrxjava.MyApplication
import com.hanifkf.daggerretrofitrxjava.model.Persons
import com.hanifkf.daggerretrofitrxjava.model.Result
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {
    var count : MutableLiveData<List<Result>> = MutableLiveData()


    @SuppressLint("CheckResult")
    fun getPersons() : LiveData<List<Result>>{
        apiInterface.getPersons().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
        return count
    }

    private fun handleResponse(persons: Persons) {

        count.value = persons.result
    }

    private fun handleError(error: Throwable) {

        Log.d("ERR", error.localizedMessage)
    }
}