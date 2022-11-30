package com.jonfhancock.acmeincrouting.data.remote

import com.jonfhancock.acmeincrouting.data.remote.model.RemoteShipmentsAndDrivers
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Language
import org.junit.Assert
import org.junit.Test

internal class DefaultShipmentsAndDriversServiceTest {
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
    fun `test json parsing`() {
        val service = DefaultShipmentsAndDriversService(jsonData)
        val result = runBlocking {
            service.getShipmentsAndDrivers()
        }
        Assert.assertEquals(
            RemoteShipmentsAndDrivers(
                listOf(
                    "215 Osinski Manors",
                    "63187 Volkman Garden Suite 447",
                    "1797 Adolf Island Apt. 744"
                ),
                listOf(
                    "Everardo Welch",
                    "Orval Mayert"
                )
            ),
            result
        )
    }
}