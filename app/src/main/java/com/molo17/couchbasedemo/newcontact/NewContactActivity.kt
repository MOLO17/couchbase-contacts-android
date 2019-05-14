package com.molo17.couchbasedemo.newcontact

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
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

    /// STEP 2
    /// Declare viewModel variable and init it lazily
//    private val viewModel: NewContactViewModel
//        get() = ViewModelProviders.of(this, factory).get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        fab.setOnClickListener {
            saveButtonPressed()
        }
    }

    /// STEP 23
    /// Retrieve data from the views, check nullability and invoke saveContact function.
    private fun saveButtonPressed() {
//        val name = nameEditText.textString
//        val surname = surnameEditText.textString
//        val phoneNumber = phoneNumberEditText.textString
//        val email = emailEditText.textString
//
//        viewModel.saveContact(name, surname, phoneNumber, email) { success ->
//            if (success) {
//                onBackPressed()
//            }
//        }
    }
}
