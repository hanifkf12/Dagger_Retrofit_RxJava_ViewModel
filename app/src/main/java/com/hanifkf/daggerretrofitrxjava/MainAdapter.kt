package com.hanifkf.daggerretrofitrxjava

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanifkf.daggerretrofitrxjava.model.Result
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter(private val context: Context, private val list: List<Result>) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(result: Result){
            itemView.name_text.text = result.firstName +" "+result.lastName
        }
    }

}