package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment1 : Fragment() {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_1, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_frag1.setOnClickListener {

            if (activity is BottomnavigActivity) {
                val input = edt_frag1.text.toString()
                (activity as BottomnavigActivity).test(0, input)
            }else{

            }
        }

    }

    fun setTextFrag(data: String) {
        txt_frag1.text = data
    }

}


