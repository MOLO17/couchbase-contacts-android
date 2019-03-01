package com.molo17.couchbasedemo.contacts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.couchbase.lite.*
import com.molo17.couchbasedemo.Constants.dbName
import com.molo17.couchbasedemo.Constants.type
import com.molo17.couchbasedemo.Contact


/**
 * Created by Matteo Sist on 28/02/2019.
 */
class ContactsViewModel(val context: Context) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public functions
    ///////////////////////////////////////////////////////////////////////////

    fun getContacts(): LiveData<List<Contact>> {
        return contacts
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private functions
    ///////////////////////////////////////////////////////////////////////////
    private fun loadContacts() {
        val query = QueryBuilder.select(SelectResult.all())
            .from(DataSource.database(database))
            .where(Expression.property(type).equalTo(Expression.string(Contact::class.java.simpleName)))

        val token = query.addChangeListener { change: QueryChange ->
            change.results.allResults().map { it.getDictionary(dbName).toMap() }.map(::Contact)
                .also(contacts::setValue)
        }

        disposable = Disposable(query, token)
    }

    fun deleteContact(contactId: String) {
        database.getDocument(contactId)?.let(database::delete)?.also { loadContacts() }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Private properties
    ///////////////////////////////////////////////////////////////////////////

    private val contacts: MutableLiveData<List<Contact>> by lazy {
        MutableLiveData<List<Contact>>().also {
            loadContacts()
        }
    }

    private val database: Database by lazy {
        val config = DatabaseConfiguration(context)
        Database(dbName, config)
    }

    private var disposable: Disposable? = null
}

class Disposable(val query: Query, val token: ListenerToken) {

    fun dispose() {
        query.removeChangeListener(token)
    }
}