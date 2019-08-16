package com.example.myapplication

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
import android.view.LayoutInflater as LayoutInflater1

class MainActivity : AppCompatActivity(), CustomItemClickListener {

    val recycAdapter: PersonAdapter by lazy {
        PersonAdapter().apply { customItemClickListener = this@MainActivity }
    }

    var ADD_NAME = 1
    var EDIT_NAME = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        additems()
        adapterexecute()

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
                         adapterexecute()
                         }
                     .setNegativeButton("Edit") { dialog, _ ->
                         val person = recycAdapter.personNames[position]
                         val intent = Intent(this, InputSaveActivity::class.java)
                            intent.putExtra(EXTRA_POS, position)
                            intent.putExtra(EXTRA_FNAME,person.fname)
                            intent.putExtra(EXTRA_MNAME,person.mname)
                            intent.putExtra(EXTRA_LNAME,person.lname)
                         startActivityForResult(intent, EDIT_NAME)
                     }
        val alert = dialogBuilder.create()
        alert.setTitle("Warning")
        alert.show()
    }

    override fun onActivityResult(requestCode : Int, resultCode: Int, data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        val firstname = data?.getStringExtra(EXTRA_FNAME) ?: ""
        val lastname = data?.getStringExtra(EXTRA_LNAME) ?: ""
        val middlename = data?.getStringExtra(EXTRA_MNAME) ?: ""
        if (requestCode == ADD_NAME){
            if(resultCode == Activity.RESULT_OK){
                val person = Person(firstname, middlename, lastname)
                recycAdapter.personNames.add(person)
                adapterexecute()
            }
        }else if(requestCode == EDIT_NAME){
            if(resultCode == Activity.RESULT_OK){
                val person = Person(firstname, middlename, lastname)
                val position = data?.getIntExtra(EXTRA_POS, -1) ?: -1
                if (position != -1) {
                    recycAdapter.personNames[position] = person
                    recycAdapter.notifyItemChanged(position)
                }
                //recycAdapter.personNames.add(person)
                //adapterexecute()

            }
        }

    }


    private fun adapterexecute(){
        recycList.adapter = recycAdapter
        recycList.layoutManager = LinearLayoutManager(this)

    }


    private fun additems(){
//        Toast.makeText(this@MainActivity,"Executed",Toast.LENGTH_SHORT).show()
        recycAdapter.personNames.add(Person("Lance","T.", "Bernardo"))
        recycAdapter.personNames.add(Person("Juan","C.", "Dela Cruz"))
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
