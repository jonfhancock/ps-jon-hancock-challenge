package com.jonfhancock.acmeincrouting.data.remote.model

@kotlinx.serialization.Serializable
data class RemoteShipmentsAndDrivers(
    val shipments: List<String>,
    val drivers: List<String>
)