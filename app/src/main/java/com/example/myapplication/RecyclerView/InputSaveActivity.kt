package com.example.myapplication.RecyclerView

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Model.*
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_inputsave.*

class InputSaveActivity : AppCompatActivity() {
    private var pos = -1
    private var photo = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputsave)


        pos = intent.getIntExtra(EXTRA_POS, -1)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val fname = intent.getStringExtra(EXTRA_FNAME) ?: ""
        val email = intent.getStringExtra(EXTRA_EMAIL) ?: ""
        val lname = intent.getStringExtra(EXTRA_LNAME) ?: ""
        var photo = intent.getStringExtra(EXTRA_PHOTO) ?: ""


        edt_id.setText(""+id)
        edt_fname.setText(fname)
        edt_email.setText(email)
        edt_lname.setText(lname)
        Glide.with(this)
            .load(photo)
            .apply(RequestOptions.circleCropTransform())
            .into(imgres_photo)


        btn_save.setOnClickListener {
            // add new item
            if (edt_id.text.isNotEmpty() && edt_fname.text.isNotEmpty() && edt_email.text.isNotEmpty() && edt_lname.text.isNotEmpty()) {
                val result = Intent()
                result.putExtra(EXTRA_POS, pos)
                result.putExtra(EXTRA_ID, edt_id.text.toString().toInt())
                result.putExtra(EXTRA_FNAME, edt_fname.text.toString())
                result.putExtra(EXTRA_EMAIL, edt_email.text.toString())
                result.putExtra(EXTRA_LNAME, edt_lname.text.toString())
                result.putExtra(EXTRA_PHOTO, photo)
                setResult(Activity.RESULT_OK, result)
                finish()
            } else {
                setResult(Activity.RESULT_CANCELED)
                Toast.makeText(this, "No item Saved", Toast.LENGTH_SHORT).show()
            }

        }

    }
}




