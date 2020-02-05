package com.molo17.couchbasedemo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.molo17.couchbasedemo.data.SyncManager

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
class SyncLifecycleObserver(private val syncManager: SyncManager) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        syncManager.startSync()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        syncManager.stopSync()
    }
}