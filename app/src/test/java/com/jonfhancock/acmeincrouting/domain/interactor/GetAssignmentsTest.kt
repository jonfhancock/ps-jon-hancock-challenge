package com.jonfhancock.acmeincrouting.domain.interactor

import com.jonfhancock.acmeincrouting.domain.ShipmentAndDriverRepository
import com.jonfhancock.acmeincrouting.domain.model.Assignment
import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class GetAssignmentsTest {

    private fun buildInteractor(drivers: List<Driver>, shipments: List<Shipment>) = GetAssignments(
        object : ShipmentAndDriverRepository {
            override suspend fun getDrivers(): List<Driver> {
                return drivers
            }

            override suspend fun getShipments(): List<Shipment> {
                return shipments
            }
        }, CalculateSuitabilityScore(
            evenStreetNameScoreMultiplier = 1.5f,
            oddStreetNameScoreMultiplier = 1f,
            sharedFactorsBonusMultiplier = .5f
        )
    )


    @Test
    fun `get assignments test single driver and shipment`() {
        val drivers = listOf(Driver("aa"))
        val shipments = listOf(Shipment("aa"))
        val getAssignments = buildInteractor(drivers, shipments)
        val assignments = runBlocking {
            getAssignments()

        }
        Assert.assertEquals(
            listOf(Assignment(Driver("aa"), Shipment("aa"))), assignments
        )

    }

    @Test
    fun `get assignments test two drivers and two shipments`() {
        val drivers = listOf(Driver("aa"), Driver("bb"))
        val shipments = listOf(Shipment("aaa"), Shipment("aa"))
        val getAssignments = buildInteractor(drivers, shipments)
        val assignments = runBlocking {
            getAssignments()

        }
        Assert.assertEquals(
            listOf(
                Assignment(Driver("bb"), Shipment("aaa")),
                Assignment(Driver("aa"), Shipment("aa"))
            ), assignments
        )

    }

    @Test
    fun `get assignments test`() {
        val drivers = listOf(Driver("aabb"), Driver("bbaa"))
        val shipments = listOf(Shipment("aaa"), Shipment("aa"))
        val getAssignments = buildInteractor(drivers, shipments)
        val assignments = runBlocking {
            getAssignments()

        }
        Assert.assertEquals(
            listOf(
                Assignment(Driver("aabb"), Shipment("aaa")),
                Assignment(Driver("bbaa"), Shipment("aa"))
            ), assignments
        )

    }

    @Test
    fun `get assignments test 2`() {
        val drivers = listOf(Driver("aabb"), Driver("bbaa"), Driver("aabba"), Driver("bbaaa"))
        val shipments = listOf(Shipment("aaa"), Shipment("aa"), Shipment("aaaa"), Shipment("aaaaa"))
        val getAssignments = buildInteractor(drivers, shipments)
        val assignments = runBlocking {
            getAssignments()

        }
        Assert.assertEquals(
            listOf(
                Assignment(Driver("aabb"), Shipment("aaa")),
                Assignment(Driver("bbaa"), Shipment("aa")),
                Assignment(Driver("aabba"), Shipment("aaaa")),
                Assignment(Driver("bbaaa"), Shipment("aaaaa")),
            ), assignments
        )

    }
}