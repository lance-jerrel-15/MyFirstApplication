package com.example.myapplication.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Model.CustomItemClickListener
import com.example.myapplication.Model.Person
import com.example.myapplication.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlin.coroutines.coroutineContext

class PersonAdapter : RecyclerView.Adapter<ViewHolder>() {



    var personNames = ArrayList<Person>()
    var customItemClickListener: CustomItemClickListener? = null

    override fun getItemCount(): Int {
        return personNames.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = personNames[position]
        //holder.imgphoto.setImageResource(item.photo)
        holder.fname.text = item.fname
        holder.email.text = item.email
        holder.lname.text = item.lname

        //Glide.with(holder.imgphoto)
        Glide.with(holder.itemView.context)
            .load(item.photo)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgphoto)


        holder.relayout.setOnLongClickListener{
            //Toast.makeText(holder.itemView.context,"",Toast.LENGTH_SHORT).show()
            customItemClickListener?.onItemClicked(position, item.fname)
            true
        }

        holder.relayout.setOnClickListener {
            customItemClickListener?.onItemClickedView(position)
            Toast.makeText(holder.itemView.context, "Item Number"+ personNames[position],Toast.LENGTH_SHORT).show()
        }

        holder.fname.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Total = "+ personNames.size,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }
}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgphoto = itemView.imgrecyc_photo
        var fname = itemView.txtrecyc_fname
        var email = itemView.txtrecyc_email
        var lname = itemView.txtrecyc_lname
        var relayout = itemView.recycler_layout


    }


