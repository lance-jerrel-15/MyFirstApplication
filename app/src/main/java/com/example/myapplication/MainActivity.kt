package com.example.myapplication

import Builder.Data
import Builder.NameList
import Builder.NameServices
import Builder.NameView
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.system.Os.remove
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_inputsave.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.recyclerview_item.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import android.view.LayoutInflater as LayoutInflater1

class MainActivity : AppCompatActivity(), CustomItemClickListener {

    val recycAdapter: PersonAdapter by lazy {
        PersonAdapter().apply { customItemClickListener = this@MainActivity }
    }

    var ADD_NAME = 1
    var EDIT_NAME = 2
    var VIEW_NAME = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        adapterexecute()
        additems()

        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/

            val intent = Intent(this, InputSaveActivity::class.java)
            startActivityForResult(intent, ADD_NAME)
        }
    }


    override fun onItemClicked(position: Int, fName: String) {

        Toast.makeText(this@MainActivity, "$position Selected",Toast.LENGTH_SHORT).show()
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Choose your Action?")

                     .setPositiveButton("Delete") { _, _ ->
                         recycAdapter.personNames.removeAt(position)
                         recycAdapter.notifyItemRemoved(position)
                         Toast.makeText(this@MainActivity, "$fName Deleted",Toast.LENGTH_SHORT).show()
                         }
                     .setNegativeButton("Edit") { dialog, _ ->
                         val person = recycAdapter.personNames[position]
                         val intent = Intent(this, InputSaveActivity::class.java)
                            intent.putExtra(EXTRA_POS, position)
                            intent.putExtra(EXTRA_ID,person.id)
                            intent.putExtra(EXTRA_FNAME,person.fname)
                            intent.putExtra(EXTRA_EMAIL,person.email)
                            intent.putExtra(EXTRA_LNAME,person.lname)
                            intent.putExtra(EXTRA_PHOTO,person.photo)
                         startActivityForResult(intent, EDIT_NAME)
                     }
        val alert = dialogBuilder.create()
        alert.setTitle("Warning")
        alert.show()
    }

    override fun onItemClickedView(position: Int) {

        val person = recycAdapter.personNames[position]
        val intent = Intent(this, InputViewActivity::class.java)
        intent.putExtra(EXTRA_POS, position)
        intent.putExtra(EXTRA_ID,person.id)
        intent.putExtra(EXTRA_FNAME,person.fname)
        intent.putExtra(EXTRA_EMAIL,person.email)
        intent.putExtra(EXTRA_LNAME,person.lname)
        intent.putExtra(EXTRA_PHOTO,person.photo)
        startActivityForResult(intent, VIEW_NAME)



        /*val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val nameServices = retrofit.create(NameServices::class.java)
        val requestcall = nameServices.getNameView()

        requestcall.enqueue(object: Callback<NameView>{
            override fun onFailure(call: Call<NameView>, t: Throwable) {

            }

            override fun onResponse(call: Call<NameView>, response: Response<NameView>) {
                if(response.isSuccessful && response.body() != null) {
                    val names = (response.body() as NameList).data
                    for (name in names) {
                        val person = Person(name.id, name.firstName, name.lastName, name.email, name.avatar)
                        recycAdapter.personNames.add(person)
                    }
                    recycAdapter.notifyDataSetChanged()
                }
            }
        })*/

    }

    override fun onActivityResult(requestCode : Int, resultCode: Int, data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)


        val id = data?.getIntExtra(EXTRA_ID, 0)?: 0
        val firstname = data?.getStringExtra(EXTRA_FNAME) ?: ""
        val lastname = data?.getStringExtra(EXTRA_LNAME) ?: ""
        val email = data?.getStringExtra(EXTRA_EMAIL) ?: ""
        val photoname = data?.getStringExtra(EXTRA_PHOTO) ?: ""

        if (requestCode == ADD_NAME){
            if(resultCode == Activity.RESULT_OK){
                val person = Person(id, firstname, email, lastname, photoname)
                recycAdapter.personNames.add(person)
                recycAdapter.notifyDataSetChanged()
            }
        }else if(requestCode == EDIT_NAME){
            if(resultCode == Activity.RESULT_OK){
                val person = Person(id, firstname, email, lastname, photoname)
                val position = data?.getIntExtra(EXTRA_POS, -1) ?: -1
                if (position != -1) {
                    recycAdapter.personNames[position] = person
                    recycAdapter.notifyItemChanged(position)
                }
            }
        }

    }


    private fun adapterexecute() {
        recycList.adapter = recycAdapter
        recycList.layoutManager = LinearLayoutManager(this)
    }


    private fun additems(){
//        Toast.makeText(this@MainActivity,"Executed",Toast.LENGTH_SHORT).show()
//        recycAdapter.personNames.add(Person("Lance","T.", "Bernardo",R.drawable.ic_launcher_background))

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val nameServices = retrofit.create(NameServices::class.java)
            val requestcall = nameServices.getNameList()

            requestcall.enqueue(object: Callback<NameList>{
                override fun onResponse(call: Call<NameList>, response: Response<NameList>) {
                    if(response.isSuccessful && response.body() != null) {
                        val names = (response.body() as NameList).data
                        for (name in names) {
                            val person = Person(name.id, name.firstName, name.lastName, name.email, name.avatar)
                            recycAdapter.personNames.add(person)
                        }
                        recycAdapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<NameList>, t: Throwable) {
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
