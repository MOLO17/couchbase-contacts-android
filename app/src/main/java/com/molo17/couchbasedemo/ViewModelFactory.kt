package com.molo17.couchbasedemo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molo17.couchbasedemo.contactdetail.ContactDetailViewModel
import com.molo17.couchbasedemo.contacts.ContactsViewModel
import com.molo17.couchbasedemo.newcontact.NewContactViewModel

/**
 * Created by Matteo Sist on 28/02/2019.
 */
class ViewModelFactory(val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ContactsViewModel::class.java -> ContactsViewModel(
                context.applicationContext
            )
            NewContactViewModel::class.java -> NewContactViewModel(
                context.applicationContext
            )
            ContactDetailViewModel::class.java -> ContactDetailViewModel(
                context.applicationContext
            )
            else -> error("Wrong cast")
        } as T
    }
}