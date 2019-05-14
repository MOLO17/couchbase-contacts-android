package com.molo17.couchbasedemo

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.couchbase.lite.ListenerToken
import java.net.URI


/**
 * Created by Matteo Sist on 01/03/2019.
 */
class ReplicatorManager(private val context: Context, remoteUrl: URI): LifecycleObserver {

    /// STEP 41
    /// Declare replication variable.
//    private val replicator: Replicator

    /// STEP 40
    /// Declare database variable and init it lazily.
//    private val database: Database by lazy {
//        val config = DatabaseConfiguration(context)
//        Database(Constants.dbName, config)
//    }

    private var token: ListenerToken? = null

    init {
        /// STEP 42
        /// Declare and init replicator with a remote url, a replicator type, continuous option enable or not and a basic authentication.
//        val config = try { URLEndpoint(remoteUrl) } catch (e: IllegalArgumentException) { null }
//            ?.let { ReplicatorConfiguration(database, it) }
//        config?.replicatorType = ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL
//        config?.isContinuous = true
//
//        replicator = config?.let(::Replicator) ?: error("ReplicatorConfiguration can not be null")
    }

    /// STEP 43
    /// Start replication function. Register a listener to listen replication status and start the replication.
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    fun startReplication() {
//        token = replicator.addChangeListener { listener ->
//            listener.status.error?.let(::print)
//        }
//        replicator.start()
//    }

    /// STEP 44
    /// Stop replication function. Deregister the replication status listener and stop the replication.
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    fun stopReplication() {
//        token?.let(replicator::removeChangeListener)
//        replicator.stop()
//    }



}