package com.example.roomdatabase

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
   lateinit var txt : TextView
   lateinit var name : EditText
   lateinit var btn : Button
   lateinit var phone : EditText
    lateinit var database: ContextDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        btn = findViewById(R.id.btn)
        txt = findViewById(R.id.txt)
        database = Room.databaseBuilder(applicationContext,ContextDatabase::class.java,"contactDB").build()

        btn.setOnClickListener {
            GlobalScope.launch {
              database.contactDao().insertContact(Contact(0, name.text.toString(),phone.text.toString()))
                name.text = null
                phone.text = null

            }
        }


        txt.setOnClickListener {
            database.contactDao().getContact().observe(this, Observer {
                Log.d(TAG, "onCreate: "+it.toString())
                txt.text = it.toString()

            })
        }

    }
}