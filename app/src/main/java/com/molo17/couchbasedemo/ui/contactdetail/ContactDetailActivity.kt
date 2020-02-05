package com.molo17.couchbasedemo.ui.contactdetail

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.ContactDetailFactory
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.data.Contact

class ContactDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_CONTACT_ID = "contactId"
    }

    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.saveContactFab) }
    private val nameEditText: EditText by lazy { findViewById<EditText>(R.id.nameEditText) }
    private val surnameEditText: EditText by lazy { findViewById<EditText>(R.id.surnameEditText) }
    private val phoneNumberEditText: EditText by lazy { findViewById<EditText>(R.id.phoneNumberEditText) }
    private val emailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }

    private val viewModel: ContactDetailViewModel by viewModels(ContactDetailFactory {
        val contactId = intent?.getStringExtra(KEY_CONTACT_ID) ?: error("Missing contactId!")
        ContactDetailType.Edit(contactId)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        viewModel.getContact().observe(this, Observer<Contact> { contact ->
            nameEditText.textString = contact.name
            surnameEditText.textString = contact.surname
            phoneNumberEditText.textString = contact.phoneNumber
            emailEditText.textString = contact.email
        })

        fab.setOnClickListener {
            saveButtonPressed()
        }
    }

    private fun saveButtonPressed() {
        viewModel.saveContact(
            name = nameEditText.textString,
            surname = surnameEditText.textString,
            phoneNumber = phoneNumberEditText.textString,
            email = emailEditText.textString,
            callback = this::finish
        )
    }
}

var EditText.textString: String?
    get() = text?.toString()
    set(value) = setText(value, TextView.BufferType.NORMAL)