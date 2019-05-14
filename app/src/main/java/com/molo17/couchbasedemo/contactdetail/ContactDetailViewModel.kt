package com.molo17.couchbasedemo.contactdetail

import android.content.Context
import androidx.lifecycle.ViewModel
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

    /// STEP 28
    /// View model public function to retrieve Contact using its id.
//    fun getContact(contactId: String): LiveData<Contact> {
//        loadContact(contactId)
//        return contact
//    }

    /// STEP 33
    /// View model public function to edit Contact using its id.
//    fun editContact(
//        id: String,
//        name: String?,
//        surname: String?,
//        phoneNumber: String?,
//        email: String?,
//        callback: (Boolean) -> Unit
//    ) {

    /// STEP 34
    /// Initialize Contact object.
//        val contact = Contact(
//            id = id,
//            name = name,
//            surname = surname,
//            phoneNumber = phoneNumber,
//            email = email
//        )
//
    /// STEP 35
    /// Serialize Contact object to document and update it.
//        val doc = MutableDocument(contact.id, contact.toMap())
//        doc.setString(Constants.type, Contact::class.java.simpleName)
//
//        try {
//            database.save(doc)
//            callback.invoke(true)
//        } catch (e: CouchbaseLiteException) {
//            callback.invoke(false)
//        }
//
//    }

    ///////////////////////////////////////////////////////////////////////////
    // Private properties
    ///////////////////////////////////////////////////////////////////////////

    /// STEP 35
    /// Declare private contact datasource and init it lazily.
//    private val contact: MutableLiveData<Contact> by lazy {
//        MutableLiveData<Contact>()
//    }

    /// STEP 28
    /// Private load contact function
//    private fun loadContact(contactId: String) {
//        database.getDocument(contactId)?.toMap()?.let(::Contact)?.also(contact::setValue)
//    }

    /// STEP 26
    /// Declare database variable and init it lazily.
//    private val database: Database by lazy {
//        val config = DatabaseConfiguration(context)
//        Database(Constants.dbName, config)
//    }

    private var disposable: Disposable? = null

    private var contactId: String? = null
}