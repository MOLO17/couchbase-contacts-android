package com.molo17.couchbasedemo.ui.contacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ViewModelFactory
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailActivity
import com.molo17.couchbasedemo.ui.newcontact.NewContactActivity

class ContactsActivity : AppCompatActivity() {

    private val viewModel: ContactsViewModel by viewModels(::ViewModelFactory)

    private val contactsListAdapter by lazy(::ContactsListRecyclerViewAdapter)

    private val recycleView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.contactsRecyclerView) }
    private val fab: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.fabContactsList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        with(recycleView) {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = contactsListAdapter
        }

        contactsListAdapter.onContactClickListener = this::navigateToDetail
        contactsListAdapter.onContactLongClickListener = this::deleteContact

        fab.setOnClickListener {
            startActivity(Intent(this, NewContactActivity::class.java))
        }

        loadData()
    }

    private fun loadData() {
        viewModel.getContacts().observe(this, Observer { contacts ->
            contactsListAdapter.contacts = contacts
            contactsListAdapter.notifyDataSetChanged()
        })
    }

    private fun navigateToDetail(contactId: String) {
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra(ContactDetailActivity.KEY_CONTACT_ID, contactId)
        startActivity(intent)
    }

    private fun deleteContact(contactId: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_dialog_title))
            .setMessage(getString(R.string.delete_dialog_message))
            .setPositiveButton(getString(R.string.delete_dialog_positive_button)) { _, _ ->
                viewModel.deleteContact(contactId)
            }
            .setNegativeButton(getString(R.string.delete_dialog_negative_button)) { _, _ -> }
            .create()
            .show()
    }
}
