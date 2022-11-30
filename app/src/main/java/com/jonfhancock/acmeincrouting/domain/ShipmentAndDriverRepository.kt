package com.jonfhancock.acmeincrouting.domain

import com.jonfhancock.acmeincrouting.data.remote.ShipmentAndDriverService
import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment

interface ShipmentAndDriverRepository {
    suspend fun getDrivers(): List<Driver>
    suspend fun getShipments(): List<Shipment>
}

fun ShipmentAndDriverRepository(): ShipmentAndDriverRepository {
    return DefaultShipmentAndDriverRepository(ShipmentAndDriverService())
}

class DefaultShipmentAndDriverRepository(
    private val shipmentAndDriverService: ShipmentAndDriverService
) : ShipmentAndDriverRepository {
    override suspend fun getDrivers(): List<Driver> {
        return shipmentAndDriverService.getShipmentsAndDrivers().drivers.map { Driver(it) }
    }

    override suspend fun getShipments(): List<Shipment> {
        return shipmentAndDriverService.getShipmentsAndDrivers().shipments.map { Shipment(it) }
    }

}