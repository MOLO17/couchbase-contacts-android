package com.molo17.couchbasedemo

import android.app.Application
import com.molo17.couchbasedemo.Constants.remoteDatabaseURL
import java.net.URI
import java.net.URISyntaxException

/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ContactsApplication : Application() {

    private var replicatorManager: ReplicatorManager? = null

    override fun onCreate() {
        super.onCreate()

        replicatorManager = try { URI(remoteDatabaseURL) } catch (e: URISyntaxException) { null }
            ?.takeIf { it.toString().isNotEmpty() }
            ?.let { ReplicatorManager(this, it) }
        replicatorManager?.startReplication()
    }
}