package com.molo17.couchbasedemo.ui.contactdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.molo17.couchbasedemo.data.Contact
import com.molo17.couchbasedemo.data.ContactsRepository
import com.molo17.couchbasedemo.data.createContact
import kotlinx.coroutines.launch
import java.util.UUID

sealed class ContactDetailType {
    object Create : ContactDetailType()
    data class Edit(val contactId: String) : ContactDetailType()
}

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
class ContactDetailViewModel(
    private val contactDetailType: ContactDetailType,
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // Public functions
    ///////////////////////////////////////////////////////////////////////////

    fun getContact(): LiveData<Contact> = liveData {
        if (contactDetailType is ContactDetailType.Edit) {
            emit(contactsRepository.getContact(contactDetailType.contactId))
        }
    }

    fun saveContact(
        name: String?,
        surname: String?,
        phoneNumber: String?,
        email: String?,
        callback: () -> Unit
    ) {
        viewModelScope.launch {
            val contactId = when (contactDetailType) {
                is ContactDetailType.Create -> UUID.randomUUID().toString()
                is ContactDetailType.Edit -> contactDetailType.contactId
            }
            val contact = createContact(contactId, name, surname, phoneNumber, email)
            try {
                contactsRepository.saveContact(contact)
                callback()
            } catch (e: Exception) {
                // Handle error.
            }
        }
    }

}