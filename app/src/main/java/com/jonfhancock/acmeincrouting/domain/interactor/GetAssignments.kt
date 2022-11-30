package com.jonfhancock.acmeincrouting.domain.interactor

import com.jonfhancock.acmeincrouting.domain.ShipmentAndDriverRepository
import com.jonfhancock.acmeincrouting.domain.model.Assignment
import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment

class GetAssignments(
    private val repository: ShipmentAndDriverRepository,
    private val calculateSuitabilityScore: CalculateSuitabilityScore
) {
    suspend operator fun invoke(): List<Assignment> {
        val drivers = repository.getDrivers()
        val shipments = repository.getShipments()

        val scores: Map<Shipment, Map<Driver, Float>> =
            getSuitabilityScores(shipments, drivers)

        val availableDrivers = drivers.toMutableList()
        return scores.map { (shipment, driverScores) ->
            val (driver, score) = driverScores
                .filterKeys { availableDrivers.contains(it) }
                .maxBy { it.value }
            availableDrivers.remove(driver)
            Assignment(driver, shipment)
        }


    }

    private fun getSuitabilityScores(shipments: List<Shipment>, drivers: List<Driver>) =
        shipments.associateWith { shipment ->
            drivers.associateWith { driver -> calculateSuitabilityScore(shipment, driver) }
        }


}