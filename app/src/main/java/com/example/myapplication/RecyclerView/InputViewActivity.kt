package com.example.myapplication.RecyclerView

import Builder.NameServices
import Builder.NameView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Model.BASE_URL
import com.example.myapplication.Model.EXTRA_ID
import com.example.myapplication.Model.Person
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_input_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InputViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_view)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val nameServices = retrofit.create(NameServices::class.java)
        val requestcall = nameServices.getNameView(id.toString())

        requestcall.enqueue(object: Callback<NameView> {
            override fun onResponse(call: Call<NameView>, response: Response<NameView>) {
                if(response.isSuccessful && response.body() != null) {
                    val names = (response.body() as NameView).data
                    Person(
                        names.id,
                        names.firstName,
                        names.lastName,
                        names.email,
                        names.avatar
                    )

                    txtV_id.text = "ID: "+ names.id
                    txtV_fname.text = "First Name: "+ names.firstName
                    txtV_lname.text = "Last Name: " + names.lastName
                    txtV_email.text = "Email: " + names.email

                    Glide.with(baseContext)
                        .load(names.avatar)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imgV_photo)

                }
            }
            override fun onFailure(call: Call<NameView>, t: Throwable) {
            }
        })


    }
}
