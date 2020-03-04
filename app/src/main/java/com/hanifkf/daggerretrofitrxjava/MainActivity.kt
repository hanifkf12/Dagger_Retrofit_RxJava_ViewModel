package com.hanifkf.daggerretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanifkf.daggerretrofitrxjava.adapter.MainAdapter
import com.hanifkf.daggerretrofitrxjava.model.PersonParams
import com.hanifkf.daggerretrofitrxjava.model.Result
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import com.hanifkf.daggerretrofitrxjava.viewmodel.PersonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mApiInterface : ApiInterface
    @Inject
    lateinit var personViewModel : PersonViewModel
    @Inject
    lateinit var coba: Coba

    private lateinit var mainAdapter: MainAdapter

    private lateinit var data:MutableList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        data = mutableListOf()
        mainAdapter = MainAdapter(this, data,{
            Toast.makeText(this, it.firstName,Toast.LENGTH_SHORT).show()
        },{
            Toast.makeText(this, "Long "+it.firstName,Toast.LENGTH_SHORT).show()

        })

        rec_person.layoutManager = LinearLayoutManager(this)
        rec_person.adapter = mainAdapter
        personViewModel.count.observe(this, Observer {
            data.clear()
            data.addAll(it!!)
            mainAdapter.notifyDataSetChanged()

        })
        personViewModel.status.observe(this, Observer {
            progressBar.visibility = if(it)View.VISIBLE else View.GONE
        })

        personViewModel.getPersons()
        coba.cetak()
        fab.setOnClickListener {
            var params = PersonParams("Hanif ff ", "heheasdasd")
            personViewModel.createPerson(params)
        }

    }

}
