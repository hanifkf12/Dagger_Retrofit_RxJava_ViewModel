package com.hanifkf.daggerretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanifkf.daggerretrofitrxjava.adapter.MainAdapter
import com.hanifkf.daggerretrofitrxjava.adapter.OnClickItemKu
import com.hanifkf.daggerretrofitrxjava.model.Result
import com.hanifkf.daggerretrofitrxjava.network.ApiInterface
import com.hanifkf.daggerretrofitrxjava.viewmodel.PersonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnClickItemKu {

    @Inject
    lateinit var mApiInterface : ApiInterface
    @Inject
    lateinit var personViewModel : PersonViewModel

    private lateinit var mainAdapter: MainAdapter

    private lateinit var data:MutableList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        data = mutableListOf()
        mainAdapter =
            MainAdapter(this, data,this)

        rec_person.layoutManager = LinearLayoutManager(this)
        rec_person.adapter = mainAdapter
        personViewModel.getPersons().observe(this, Observer {
            data.clear()
            data.addAll(it)
            mainAdapter.notifyDataSetChanged()

        })
        personViewModel.getStatus().observe(this, Observer {
            Log.d("STATUS", it)
        })

    }

    override fun onClickItemKu(result: Result) {
        Toast.makeText(this, result.firstName,Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(result: Result) {
        Toast.makeText(this, "Long "+result.firstName,Toast.LENGTH_SHORT).show()
    }

}
