package com.hanifkf.daggerretrofitrxjava.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hanifkf.daggerretrofitrxjava.MyApplication
import com.hanifkf.daggerretrofitrxjava.R

class CreatePersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_person)
    }
}
