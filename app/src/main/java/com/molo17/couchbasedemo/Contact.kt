package com.molo17.couchbasedemo

/**
 * Created by Matteo Sist on 28/02/2019.
 */

class Contact(private val map: MutableMap<String, Any?>) {
    var id: String by map
    var name: String? by map
    var surname: String? by map
    var phoneNumber: String? by map
    var email: String? by map

    fun toMap(): Map<String, Any?> = map
}

fun Contact(
    id: String,
    name: String?,
    surname: String?,
    phoneNumber: String?,
    email: String?
) = Contact(mutableMapOf()).also {
    it.id = id
    it.name = name
    it.surname = surname
    it.phoneNumber = phoneNumber
    it.email = email
}