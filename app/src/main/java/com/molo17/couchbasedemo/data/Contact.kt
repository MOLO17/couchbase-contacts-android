package com.molo17.couchbasedemo.data

/**
 * Created by Matteo Sist on 28/02/2019.
 */

data class Contact(
    val id: String,
    val name: String?,
    val surname: String?,
    val phoneNumber: String?,
    val email: String?
)

fun createContact(
    id: String,
    name: String?,
    surname: String?,
    phoneNumber: String?,
    email: String?
): Contact = Contact(
    id = id,
    name = name?.takeIf { it.isNotBlank() },
    surname = surname?.takeIf { it.isNotBlank() },
    phoneNumber = phoneNumber?.takeIf { it.isNotBlank() },
    email = email?.takeIf { it.isNotBlank() }
)