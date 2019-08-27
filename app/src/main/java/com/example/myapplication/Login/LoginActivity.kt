package com.example.myapplication.Login

import Builder.LoginInfo
import Builder.NameServices
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.Model.BASE_URL
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        btn_login_info.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            val email = edt_username.text.toString()
            val pass = edt_password.text.toString()
            val nameServices = retrofit.create(NameServices::class.java)
            val requestcalls = nameServices.getLogin(email,pass)

            requestcalls.enqueue(object : Callback<LoginInfo> {
                override fun onFailure(call: Call<LoginInfo>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error! Please Try Again", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<LoginInfo>, response: Response<LoginInfo>) {
                    if (response.isSuccessful) {
                        val names = (response.body() as LoginInfo).token
                        Log.i("Login", response.toString())
                        Toast.makeText(this@LoginActivity,names, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@LoginActivity, "Invalid Credential", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        btn_backlog.setOnClickListener {
            finish()
        }



    }
}