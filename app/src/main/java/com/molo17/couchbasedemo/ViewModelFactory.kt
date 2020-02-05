package com.molo17.couchbasedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailType
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailViewModel
import com.molo17.couchbasedemo.ui.contacts.ContactsViewModel
import com.molo17.couchbasedemo.data.ContactsRepository
import com.molo17.couchbasedemo.data.CouchbaseContactsRepository

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
class ViewModelFactory : CommonViewModelFactory()

fun ContactDetailFactory(
    contactDetailTypeProvider: () -> ContactDetailType
): () -> ViewModelProvider.Factory = {
    object : CommonViewModelFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when (modelClass) {
                ContactDetailViewModel::class.java -> ContactDetailViewModel(
                    contactDetailType = contactDetailTypeProvider(),
                    contactsRepository = contactsRepository
                ) as T
                else -> super.create(modelClass)
            }
        }
    }
}

abstract class CommonViewModelFactory : ViewModelProvider.Factory {

    protected val contactsRepository: ContactsRepository = CouchbaseContactsRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ContactsViewModel::class.java -> ContactsViewModel(contactsRepository)
            else -> error("Unhandled ViewModel of type ${modelClass.simpleName}")
        } as T
    }
}