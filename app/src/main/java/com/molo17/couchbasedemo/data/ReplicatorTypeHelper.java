package com.molo17.couchbasedemo.data;

import com.couchbase.lite.ReplicatorConfiguration;

/**
 * Workaround for tracked Couchbase bug:
 * https://github.com/couchbase/couchbase-lite-android-ce/issues/32
 * <p>
 * Created by Damiano Giusti on 2020-02-05.
 */
class ReplicatorTypeHelper {

    static void setReplicatorType(
        final ReplicatorConfiguration configuration,
        final boolean pull,
        final boolean push
    ) {
        if (pull && push) {
            configuration.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);
        } else if (pull) {
            configuration.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PULL);
        } else if (push) {
            configuration.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH);
        }
    }
}
