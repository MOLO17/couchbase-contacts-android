package com.molo17.couchbasedemo.contactdetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import com.couchbase.lite.MutableDocument
import com.molo17.couchbasedemo.Constants
import com.molo17.couchbasedemo.Contact
import com.molo17.couchbasedemo.contacts.Disposable

/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ContactDetailViewModel(val context: Context) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public functions
    ///////////////////////////////////////////////////////////////////////////

    fun getContact(contactId: String): LiveData<Contact> {
        loadContact(contactId)
        return contact
    }

    fun editContact(
        id: String,
        name: String?,
        surname: String?,
        phoneNumber: String?,
        email: String?,
        callback: (Boolean) -> Unit
    ) {
        val contact = Contact(
            id = id,
            name = name,
            surname = surname,
            phoneNumber = phoneNumber,
            email = email
        )

        val doc = MutableDocument(contact.id, contact.toMap())
        doc.setString(Constants.type, Contact::class.java.simpleName)

        try {
            database.save(doc)
            callback.invoke(true)
        } catch (e: CouchbaseLiteException) {
            callback.invoke(false)
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // Private properties
    ///////////////////////////////////////////////////////////////////////////

    private val contact: MutableLiveData<Contact> by lazy {
        MutableLiveData<Contact>()
    }

    private fun loadContact(contactId: String) {
        database.getDocument(contactId)?.toMap()?.let(::Contact)?.also(contact::setValue)
    }

    private val database: Database by lazy {
        val config = DatabaseConfiguration(context)
        Database(Constants.dbName, config)
    }

    private var disposable: Disposable? = null

    private var contactId: String? = null
}