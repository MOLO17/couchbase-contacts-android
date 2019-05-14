package com.molo17.couchbasedemo.newcontact

import android.content.Context
import androidx.lifecycle.ViewModel
import com.molo17.couchbasedemo.contacts.Disposable


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

    /// STEP 17
    /// View model public function to save Contact to database.
//    fun saveContact(
//        name: String?,
//        surname: String?,
//        phoneNumber: String?,
//        email: String?,
//        callback: (Boolean) -> Unit
//    ) {
        /// STEP 18
        /// Initialize Contact object.
//        val contact = Contact(
//            id = UUID.randomUUID().toString(),
//            name = name,
//            surname = surname,
//            phoneNumber = phoneNumber,
//            email = email
//        )

        /// STEP 19
        /// Serialize Contact object to document and save it.
//        val doc = MutableDocument(contact.id, contact.toMap())
//        doc.setString(type, Contact::class.java.simpleName)
//
//        try {
//            database.save(doc)
//            callback.invoke(true)
//        } catch (e: CouchbaseLiteException) {
//            callback.invoke(false)
//        }

//    }

    /// STEP 16
    /// Declare database variable and init it lazily.
//    private val database: Database by lazy {
//        val config = DatabaseConfiguration(context)
//        Database(Constants.dbName, config)
//    }

    private var disposable: Disposable? = null
}