package com.jonfhancock.acmeincrouting.domain

import com.jonfhancock.acmeincrouting.data.remote.DefaultShipmentsAndDriversService
import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Language
import org.junit.Assert
import org.junit.Test

internal class DefaultShipmentAndDriverRepositoryTest {
    @Language("json")
    private val jsonData =
        """
    {
        "shipments": [
            "215 Osinski Manors",
            "63187 Volkman Garden Suite 447",
            "1797 Adolf Island Apt. 744"
        ],
        "drivers": [
          "Everardo Welch",
          "Orval Mayert"
        ]
    }
    """.trimIndent()

    @Test
    fun `test get drivers`() {
        val repo =
            DefaultShipmentAndDriverRepository(DefaultShipmentsAndDriversService(jsonData))

        val result = runBlocking {
            repo.getDrivers()
        }

        Assert.assertEquals(
            listOf(Driver("Everardo Welch"), Driver("Orval Mayert")),
            result
        )
    }

    @Test
    fun `test get shipments`() {
        val repo =
            DefaultShipmentAndDriverRepository(DefaultShipmentsAndDriversService(jsonData))
        val result = runBlocking {
            repo.getShipments()
        }
        Assert.assertEquals(
            listOf(
                Shipment("215", "Osinski Manors", null, null),
                Shipment("63187", "Volkman Garden", "Suite", "447"),
                Shipment("1797", "Adolf Island", "Apt.", "744")
            ),
            result
        )
    }
}