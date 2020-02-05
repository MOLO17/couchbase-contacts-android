# Android Contacts sample app

### Powered by Couchbase Lite!

This repository contains the sources of an Android sample app that demonstrates how to setup
the Couchbase Lite SDK with a Couchbase Sync Gateway for replicating data across mobile devices.

## Setup

Just clone the repository and open the project. Open the app `build.gradle` file and set the build
config properties for matching the actual configuration of your Couchbase Sync Gateway instance.

For example:

```groovy
buildConfigField("String", "SYNC_URL", '"ws://10.0.2.2:4984/example-bucket/"')
buildConfigField("String", "SYNC_USERNAME", '"user"')
buildConfigField("String", "SYNC_PASSWORD", '"password"')
```

## Authors

- [Damiano Giusti](mailto:damiano.giusti@molo17.com)
- [Matteo Sist](mailto:matteo.sist@molo17.com)

