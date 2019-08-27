package com.example.myapplication.Fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_2.*


class Fragment2 : Fragment() {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment

            return inflater.inflate(R.layout.fragment_2, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            btn_frag2.setOnClickListener {
                if (activity is BottomnavigActivity) {
                    (activity as BottomnavigActivity).test(1, edt_frag2.text.toString())
                    }
            }
    }

    fun setTextFrag(data: String){
        txt_frag2.text = data
    }
}
