package com.molo17.couchbasedemo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.couchbase.lite.CouchbaseLite
import com.molo17.couchbasedemo.data.CouchbaseContactsRepository

/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ContactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CouchbaseLite.init(this)

        ProcessLifecycleOwner.get().lifecycle
            .addObserver(SyncLifecycleObserver(CouchbaseContactsRepository))
    }
}