package com.molo17.couchbasedemo.data

import kotlinx.coroutines.flow.Flow

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
interface ContactsRepository {

    fun getAllContacts(): Flow<List<Contact>>

    suspend fun getContact(contactId: String): Contact

    suspend fun saveContact(contact: Contact)

    suspend fun deleteContact(contactId: String)
}

class NoSuchContactException : NoSuchElementException()
class SaveContactException(cause: Throwable) : Exception(cause)