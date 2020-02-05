package com.molo17.couchbasedemo.ui.newcontact

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.ContactDetailFactory
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailType
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailViewModel

class NewContactActivity : AppCompatActivity() {

    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.saveContactFab) }

    private val nameEditText: EditText by lazy { findViewById<EditText>(R.id.nameEditText) }
    private val surnameEditText: EditText by lazy { findViewById<EditText>(R.id.surnameEditText) }
    private val phoneNumberEditText: EditText by lazy { findViewById<EditText>(R.id.phoneNumberEditText) }
    private val emailEditText: EditText by lazy { findViewById<EditText>(R.id.emailEditText) }

    private val viewModel: ContactDetailViewModel by viewModels(ContactDetailFactory { ContactDetailType.Create })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        fab.setOnClickListener { saveButtonPressed() }
    }

    private fun saveButtonPressed() {
        viewModel.saveContact(
            name = nameEditText.text?.toString(),
            surname = surnameEditText.text?.toString(),
            phoneNumber = phoneNumberEditText.text?.toString(),
            email = emailEditText.text?.toString(),
            callback = this::finish
        )
    }
}
