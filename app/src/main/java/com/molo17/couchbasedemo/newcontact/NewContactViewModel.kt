package com.molo17.couchbasedemo.newcontact

import android.content.Context
import androidx.lifecycle.ViewModel
import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import com.couchbase.lite.MutableDocument
import com.molo17.couchbasedemo.Constants
import com.molo17.couchbasedemo.Constants.type
import com.molo17.couchbasedemo.Contact
import com.molo17.couchbasedemo.contacts.Disposable
import java.util.*



/**
 * Created by Matteo Sist on 28/02/2019.
 */

class NewContactViewModel(val context: Context) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public functions
    ///////////////////////////////////////////////////////////////////////////

    fun saveContact(
        name: String?,
        surname: String?,
        phoneNumber: String?,
        email: String?,
        callback: (Boolean) -> Unit
    ) {
        val contact = Contact(
            id = UUID.randomUUID().toString(),
            name = name,
            surname = surname,
            phoneNumber = phoneNumber,
            email = email
        )

        val doc = MutableDocument(contact.id, contact.toMap())
        doc.setString(type, Contact::class.java.simpleName)

        try {
            database.save(doc)
            callback.invoke(true)
        } catch (e: CouchbaseLiteException) {
            callback.invoke(false)
        }

    }

    private val database: Database by lazy {
        val config = DatabaseConfiguration(context)
        Database(Constants.dbName, config)
    }

    private var disposable: Disposable? = null
}