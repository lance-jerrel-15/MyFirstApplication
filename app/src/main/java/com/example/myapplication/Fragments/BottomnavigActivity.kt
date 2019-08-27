package com.example.myapplication.Fragments

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_bottomnavig.*
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_3.*
import java.util.ArrayList

class BottomnavigActivity : AppCompatActivity() {

    private var pos = -1


    private val pagerAdapter: MyViewPageAdapter by lazy {
        val adapter = MyViewPageAdapter(supportFragmentManager)
        adapter.addfragment(Fragment1())
        adapter.addfragment(Fragment2())
        adapter.addfragment(Fragment3())
        adapter

    }
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                println("1 pressed")
                viewpager.currentItem = 0
                pos = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_dashboard -> {
                println("2 pressed")
                viewpager.currentItem = 1
                pos = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                println("3 pressed")
                viewpager.currentItem = 2
                pos = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    fun test(pos: Int, input: String){
        Log.d(BottomnavigActivity::class.java.simpleName, "test")
        if (pos == 0) {
            (pagerAdapter.getItem(1) as Fragment2).setTextFrag(input)
//            (pagerAdapter.getItem(2) as Fragment3).setTextFrag(input)
        }else if(pos == 1){
            (pagerAdapter.getItem(0) as Fragment1).setTextFrag(input)
            (pagerAdapter.getItem(2) as Fragment3).setTextFrag(input)
        }else if(pos == 2){
//            (pagerAdapter.getItem(0) as Fragment1).setTextFrag(input)
            (pagerAdapter.getItem(1) as Fragment2).setTextFrag(input)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottomnavig)
        val navView: BottomNavigationView = findViewById(R.id.nav_bottview)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        //viewpager.currentItem = 0
        viewpager.adapter = pagerAdapter

    }

    private fun replacedFragment(fragment: Fragment){
        val fragtransac = supportFragmentManager.beginTransaction()
        fragtransac.replace(R.id.container, fragment)
        fragtransac.commit()
    }

    class MyViewPageAdapter(manager:FragmentManager) : FragmentStatePagerAdapter(manager){

        val fragmentlist : MutableList<Fragment> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return fragmentlist[position]
        }
        override fun getCount(): Int {
            return fragmentlist.size
        }
        fun addfragment(fragment: Fragment){
            fragmentlist.add(fragment)
        }
    }

}
