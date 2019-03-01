package com.molo17.couchbasedemo.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.Constants.CONTACT_ID
import com.molo17.couchbasedemo.Contact
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ViewModelFactory
import com.molo17.couchbasedemo.contactdetail.ContactDetailActivity
import com.molo17.couchbasedemo.newcontact.NewContactActivity

class ContactsActivity : AppCompatActivity() {

    private val factory by lazy { ViewModelFactory(this) }

    private val viewModel: ContactsViewModel
        get() = ViewModelProviders.of(this, factory).get()

    private val contactsListAdapter by lazy(::ContactsListRecyclerViewAdapter)

    private val recycleView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.contactsRecyclerView) }
    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.fabContactsList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        recycleView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        recycleView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = contactsListAdapter
        }

        contactsListAdapter.onContactClickListener = { contactId ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra(CONTACT_ID,  contactId)
            startActivity(intent)
        }

        contactsListAdapter.onContactLongClickListener = { contactId ->
            AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want delete contact?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteContact(contactId)
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
                .show()
        }

        fab.setOnClickListener {
            startActivity(Intent(this, NewContactActivity::class.java))
        }

        viewModel.getContacts().observe(this, Observer<List<Contact>> { contacts ->
            contactsListAdapter.contacts = contacts
            contactsListAdapter.notifyDataSetChanged()
        })
    }
}
