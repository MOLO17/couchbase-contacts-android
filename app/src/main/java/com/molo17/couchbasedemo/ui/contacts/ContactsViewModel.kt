package com.molo17.couchbasedemo.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.molo17.couchbasedemo.data.Contact
import com.molo17.couchbasedemo.data.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by Matteo Sist on 28/02/2019.
 */
class ContactsViewModel(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    fun getContacts(): LiveData<List<UiContact>> = contactsRepository
        .getAllContacts()
        .map { contacts -> contacts.map(Contact::toUiContact) }
        .asLiveData()

    ///////////////////////////////////////////////////////////////////////////
    // Private functions
    ///////////////////////////////////////////////////////////////////////////

    fun deleteContact(contactId: String) {
        viewModelScope.launch(Dispatchers.IO) { contactsRepository.deleteContact(contactId) }
    }
}

private fun Contact.toUiContact(): UiContact = UiContact(
    identifier = id,
    displayName = getDisplayName(),
    phoneNumber = getPhoneNumber()
)

private fun Contact.getDisplayName(): String {
    return listOfNotNull(name, surname).run {
        if (isEmpty()) "No name"
        else joinToString(" ")
    }
}

private fun Contact.getPhoneNumber(): String {
    return phoneNumber?.takeIf(String::isNotBlank) ?: "No phone number"
}