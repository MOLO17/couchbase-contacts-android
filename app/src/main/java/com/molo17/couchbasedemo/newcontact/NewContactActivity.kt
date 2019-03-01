package com.molo17.couchbasedemo.newcontact

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ViewModelFactory

class NewContactActivity : AppCompatActivity() {

    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.saveContactFab) }

    private val nameEditText: EditText by lazy { findViewById<EditText>(R.id.nameEditText) }
    private val surnameEditText: EditText by lazy { findViewById<EditText>(R.id.surnameEditText) }
    private val phoneNumberEditText: EditText by lazy { findViewById<EditText>(R.id.phoneNumberEditText) }
    private val emailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }

    private val factory by lazy { ViewModelFactory(this) }

    private val viewModel: NewContactViewModel
        get() = ViewModelProviders.of(this, factory).get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        fab.setOnClickListener {
            val name = nameEditText.text?.toString()
            val surname = surnameEditText.text?.toString()
            val phoneNumber = phoneNumberEditText.text?.toString()
            val email = emailEditText.text?.toString()

            viewModel.saveContact(name, surname, phoneNumber, email) { success ->
                if (success) {
                    onBackPressed()
                }
            }
        }
    }
}
