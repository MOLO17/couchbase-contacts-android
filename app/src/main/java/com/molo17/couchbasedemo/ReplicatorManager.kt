package com.molo17.couchbasedemo

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.couchbase.lite.*
import java.net.URI


/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ReplicatorManager(private val context: Context, remoteUrl: URI): LifecycleObserver {

    private val replicator: Replicator

    private val database: Database by lazy {
        val config = DatabaseConfiguration(context)
        Database(Constants.dbName, config)
    }

    private var token: ListenerToken? = null

    init {
        val config = try { URLEndpoint(remoteUrl) } catch (e: IllegalArgumentException) { null }
            ?.let { ReplicatorConfiguration(database, it) }
        config?.replicatorType = ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL
        config?.isContinuous = true

        replicator = config?.let(::Replicator) ?: error("ReplicatorConfiguration can not be null")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startReplication() {
        token = replicator.addChangeListener { listener ->
            listener.status.error?.let(::print)
        }
        replicator.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopReplication() {
        token?.let(replicator::removeChangeListener)
        replicator.stop()
    }



}