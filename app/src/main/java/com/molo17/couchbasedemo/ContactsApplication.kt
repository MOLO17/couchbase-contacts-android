package com.molo17.couchbasedemo

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.molo17.couchbasedemo.Constants.remoteDatabaseURL
import java.net.URI

/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ContactsApplication : Application() {

    /// STEP 39
    /// Declare replication manager variable.
    private var replicatorManager: ReplicatorManager? = null

    override fun onCreate() {
        super.onCreate()

        /// STEP 45
        /// Check if url string is valid and init replicationManager.
        replicatorManager = runCatching { URI(remoteDatabaseURL) }.getOrNull()
            ?.takeIf { it.toString().isNotEmpty() }
            ?.let { ReplicatorManager(this, it) }

            /// STEP 46
            /// Start replication when app enter in foreground.
            /// Stop replication when app enter in background.
            ?.also(ProcessLifecycleOwner.get().lifecycle::addObserver)
    }


}