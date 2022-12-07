package com.example.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var CreateButton = findViewById<Button>(R.id.CreateButton)
        var SearchText = findViewById<EditText>(R.id.SearchText)
        var ContactList = findViewById<RecyclerView>(R.id.ContactList)

//        SearchText.doAfterTextChanged {
//
//        }
        CreateButton.setOnClickListener{
            val crateContactActivity = Intent(this, CreateContactActivity::class.java)
            startActivity(crateContactActivity)
        }
    }
}