package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_inputsave.*
import kotlinx.android.synthetic.main.content_main.*

class InputSaveActivity : AppCompatActivity() {
    private var pos = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputsave)

        pos = intent.getIntExtra(EXTRA_POS, -1)
        val fname = intent.getStringExtra(EXTRA_FNAME) ?: ""
        val mname = intent.getStringExtra(EXTRA_MNAME) ?: ""
        val lname = intent.getStringExtra(EXTRA_LNAME) ?: ""

        edt_fname.setText(fname)
        edt_mname.setText(mname)
        edt_lname.setText(lname)

        btn_save.setOnClickListener {
            // add new item
            if (edt_fname.text.toString().isNotEmpty() || edt_mname.text.toString().isNotEmpty() || edt_lname.text.toString().isNotEmpty()) {
                val result = Intent()
                result.putExtra(EXTRA_POS, pos)
                result.putExtra(EXTRA_FNAME, edt_fname.text.toString())
                result.putExtra(EXTRA_MNAME, edt_mname.text.toString())
                result.putExtra(EXTRA_LNAME, edt_lname.text.toString())
                setResult(Activity.RESULT_OK, result)
                finish()
            } else {
                setResult(Activity.RESULT_CANCELED)
                Toast.makeText(this, "No item Saved", Toast.LENGTH_SHORT).show()
            }

        }

    }
}




