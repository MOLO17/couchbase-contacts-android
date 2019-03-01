package com.molo17.couchbasedemo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.molo17.couchbasedemo.Constants.remoteDatabaseURL
import java.net.URI

/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ContactsApplication : Application() {

    private var replicatorManager: ReplicatorManager? = null

    override fun onCreate() {
        super.onCreate()

        replicatorManager = runCatching { URI(remoteDatabaseURL) }.getOrNull()
            ?.takeIf { it.toString().isNotEmpty() }
            ?.let { ReplicatorManager(this, it) }
            ?.also(ProcessLifecycleOwner.get().lifecycle::addObserver)
    }


}