package com.molo17.couchbasedemo.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.molo17.couchbasedemo.Constants
import com.molo17.couchbasedemo.R
import com.molo17.couchbasedemo.ViewModelFactory
import com.molo17.couchbasedemo.contactdetail.ContactDetailActivity
import com.molo17.couchbasedemo.newcontact.NewContactActivity

class ContactsActivity : AppCompatActivity() {

    private val factory by lazy { ViewModelFactory(this) }

    /// STEP 2
    /// Declare viewModel variable and init it lazily
//    private val viewModel: ContactsViewModel
//        get() = ViewModelProviders.of(this, factory).get()

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

        contactsListAdapter.onContactClickListener = this::navigateToDetail

        contactsListAdapter.onContactLongClickListener  = this::deleteContact

        fab.setOnClickListener {
            startActivity(Intent(this, NewContactActivity::class.java))
        }

        loadData()
    }

    /// STEP 9
    /// LoadData function to allow Activity retrieve contacts list.
    private fun loadData() {
//        viewModel.getContacts().observe(this, Observer<List<Contact>> { contacts ->
//            contactsListAdapter.contacts = contacts
//            contactsListAdapter.notifyDataSetChanged()
//        })
    }

    /// STEP 31
    /// NavigateTo function to allow navigation from ContactViewController to ContactsDetailViewController.
    private fun navigateToDetail(contactId: String) {
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra(Constants.CONTACT_ID, contactId)
        startActivity(intent)
    }

    /// STEP 39
    /// Show confirmation dialog and invoke delete contact function.
    private fun deleteContact(contactId: String) {
//        AlertDialog.Builder(this)
//            .setTitle("Delete")
//            .setMessage("Do you want delete contact?")
//            .setPositiveButton("Yes") { _, _ ->
//                viewModel.deleteContact(contactId)
//            }
//            .setNegativeButton("No") { _, _ -> }
//            .create()
//            .show()
    }
}
