package com.molo17.couchbasedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molo17.couchbasedemo.data.ContactsRepository
import com.molo17.couchbasedemo.data.CouchbaseContactsRepository
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailType
import com.molo17.couchbasedemo.ui.contactdetail.ContactDetailViewModel
import com.molo17.couchbasedemo.ui.contacts.ContactsViewModel

/**
 * Factory for creating [ViewModel] instances.
 */
class ViewModelFactory : CommonViewModelFactory()

/**
 * Factory that is specifically intended for creating [ContactDetailViewModel] instances.
 */
fun ContactDetailFactory(contactDetailTypeProvider: () -> ContactDetailType): () -> ViewModelProvider.Factory = {
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

/**
 * Factory that contains the base definitions of the app's ViewModels.
 */
abstract class CommonViewModelFactory : ViewModelProvider.Factory {

    protected val contactsRepository: ContactsRepository = CouchbaseContactsRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ContactsViewModel::class.java -> ContactsViewModel(contactsRepository)
            else -> error("Unhandled ViewModel of type ${modelClass.simpleName}")
        } as T
    }
}