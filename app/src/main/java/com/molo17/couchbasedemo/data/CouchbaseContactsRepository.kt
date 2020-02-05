package com.molo17.couchbasedemo.data

import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.DataSource
import com.couchbase.lite.Database
import com.couchbase.lite.DatabaseConfiguration
import com.couchbase.lite.Expression
import com.couchbase.lite.MutableDocument
import com.couchbase.lite.QueryBuilder
import com.couchbase.lite.QueryChange
import com.couchbase.lite.SelectResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
class CouchbaseContactsRepository : ContactsRepository {

    companion object {
        const val databaseName = "CouchbaseDemo.db"
        const val type = "type"
        const val contactType = "Contact"
    }

    private val database: Database by lazy {
        val config = DatabaseConfiguration()
        Database(databaseName, config)
    }

    override fun getAllContacts(): Flow<List<Contact>> = callbackFlow {
        val query = QueryBuilder.select(SelectResult.all())
            .from(DataSource.database(database))
            .where(Expression.property(type).equalTo(Expression.string(contactType)))

        val token = query.addChangeListener { change: QueryChange ->
            if (change.error == null) {
                change.results.allResults()
                    .mapNotNull { it.getDictionary(databaseName)?.toMap() }
                    .map(::DbContact)
                    .map(DbContact::toContact)
                    .also { offer(it) }
            } else {
                close(change.error)
            }
        }

        invokeOnClose { query.removeChangeListener(token) }
    }

    override suspend fun getContact(contactId: String): Contact {
        val map = database.getDocument(contactId)?.toMap()
        return map?.let(::DbContact)?.toContact() ?: throw NoSuchContactException()
    }

    override suspend fun saveContact(contact: Contact) {
        val doc = MutableDocument(contact.id, contact.toDbContact().toMap())
        doc.setString(type, contactType)
        try {
            database.save(doc)
        } catch (e: CouchbaseLiteException) {
            throw SaveContactException(e)
        }
    }

    override suspend fun deleteContact(contactId: String) {
        database.getDocument(contactId)?.let(database::delete) ?: throw NoSuchContactException()
    }
}

///////////////////////////////////////////////////////////////////////////
// Persistance models
///////////////////////////////////////////////////////////////////////////

private class DbContact(valuesMap: MutableMap<String, Any?>) {
    private val map = valuesMap.withDefault { null }
    var id: String by map
    var name: String? by map
    var surname: String? by map
    var phoneNumber: String? by map
    var email: String? by map

    fun toMap(): Map<String, Any?> = map
}

private fun DbContact.toContact(): Contact = Contact(id, name, surname, phoneNumber, email)
private fun Contact.toDbContact(): DbContact = DbContact(mutableMapOf()).also { contact ->
    contact.id = id
    contact.name = name
    contact.surname = surname
    contact.email = email
    contact.phoneNumber = phoneNumber
}