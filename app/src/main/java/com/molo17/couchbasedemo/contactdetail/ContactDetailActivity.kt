package com.molo17.couchbasedemo.contactdetail

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.Constants.CONTACT_ID
import com.molo17.couchbasedemo.Contact
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ViewModelFactory

class ContactDetailActivity : AppCompatActivity() {

    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.saveContactFab) }

    private val nameEditText: EditText by lazy { findViewById<EditText>(R.id.nameEditText) }
    private val surnameEditText: EditText by lazy { findViewById<EditText>(R.id.surnameEditText) }
    private val phoneNumberEditText: EditText by lazy { findViewById<EditText>(R.id.phoneNumberEditText) }
    private val emailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }

    private val factory by lazy { ViewModelFactory(this) }

    private val viewModel: ContactDetailViewModel
        get() = ViewModelProviders.of(this, factory).get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        contactId = intent.getStringExtra(CONTACT_ID)

        contactId?.let {
            viewModel.getContact(it).observe(this, Observer<Contact> { contact ->
                nameEditText.setText(contact.name)
                surnameEditText.setText(contact.surname)
                phoneNumberEditText.setText(contact.phoneNumber)
                emailEditText.setText(contact.email)
            })
        }

        fab.setOnClickListener {
            contactId?.let {
                val name = nameEditText.text?.toString()
                val surname = surnameEditText.text?.toString()
                val phoneNumber = phoneNumberEditText.text?.toString()
                val email = emailEditText.text?.toString()

                viewModel.editContact(it, name, surname, phoneNumber, email) { success ->
                    if (success) {
                        onBackPressed()
                    }
                }
            }
        }
    }

    private var contactId: String? = null
}
