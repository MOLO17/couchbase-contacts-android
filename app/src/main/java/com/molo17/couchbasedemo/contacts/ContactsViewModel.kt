package com.molo17.couchbasedemo.contacts

import android.content.Context
import androidx.lifecycle.ViewModel
import com.couchbase.lite.ListenerToken
import com.couchbase.lite.Query


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

    /// STEP 4
    /// Function to retrieve Contacts list.
//    fun getContacts(): LiveData<List<Contact>> {
//        return contacts
//    }

    ///////////////////////////////////////////////////////////////////////////
    // Private functions
    ///////////////////////////////////////////////////////////////////////////

    /// STEP 6
    /// LoadData function to retrieve all contacts from database.
//    private fun loadContacts() {
//
    /// STEP 7
    /// Define database query to retrieve all documents having "Contact" as value of field "type".
//        val query = QueryBuilder.select(SelectResult.all())
//            .from(DataSource.database(database))
//            .where(Expression.property(type).equalTo(Expression.string(Contact::class.java.simpleName)))
//
    /// STEP 8
    /// Execute the query and subscribe to every changes of datasource.
    /// Add query and token to a disposable object to unsubscribe when ViewModel will be disposed.
//        val token = query.addChangeListener { change: QueryChange ->
//            change.results.allResults().map { it.getDictionary(dbName).toMap() }.map(::Contact)
//                .also(contacts::setValue)
//        }
//
//        disposable = Disposable(query, token)
//    }

    /// STEP 37
    /// Delete contact function to delete a contact from database with its id.
//    fun deleteContact(contactId: String) {
//
//
        /// STEP 38
        /// Retrieve document with id, delete it with properly database function and then update datasource.
//        database.getDocument(contactId)
//            ?.let(database::delete)
//            ?.also { loadContacts() }
//    }

    ///////////////////////////////////////////////////////////////////////////
    // Private properties
    ///////////////////////////////////////////////////////////////////////////

    /// STEP 3
    /// Declare contacts array and init it empty.
//    private val contacts: MutableLiveData<List<Contact>> by lazy {
//        MutableLiveData<List<Contact>>().also {
//            loadContacts()
//        }
//    }

    /// STEP 5
    /// Declare database variable and init it lazily.
//    private val database: Database by lazy {
//        val config = DatabaseConfiguration(context)
//        Database(dbName, config)
//    }

    private var disposable: Disposable? = null
}

class Disposable(val query: Query, val token: ListenerToken) {

    fun dispose() {
        query.removeChangeListener(token)
    }
}