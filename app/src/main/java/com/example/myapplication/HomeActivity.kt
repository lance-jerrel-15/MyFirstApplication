package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Fragments.BottomnavigActivity
import com.example.myapplication.Login.LoginActivity
import com.example.myapplication.RecyclerView.MainActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_retrofit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, 0)
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, 0)
        }

        btn_frag.setOnClickListener{
            val intent = Intent(this, BottomnavigActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }


}
