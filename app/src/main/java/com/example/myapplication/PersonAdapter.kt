package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class PersonAdapter : RecyclerView.Adapter<ViewHolder>() {

    var personNames = ArrayList<Person>()
    var customItemClickListener: CustomItemClickListener? = null

    override fun getItemCount(): Int {
        return personNames.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = personNames[position]
        holder.fname.text = item.fname
        holder.mname.text = item.mname
        holder.lname.text = item.lname

        holder.relayout.setOnClickListener{
            customItemClickListener?.onItemClicked(position, item.fname)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }
}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var fname = itemView.txtrecyc_fname
        var mname = itemView.txtrecyc_mname
        var lname = itemView.txtrecyc_lname
        var relayout = itemView.recycler_layout

    }
