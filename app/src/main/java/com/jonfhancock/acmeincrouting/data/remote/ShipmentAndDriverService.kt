package com.jonfhancock.acmeincrouting.data.remote

import com.jonfhancock.acmeincrouting.data.remote.model.RemoteShipmentsAndDrivers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.Language

interface ShipmentAndDriverService {
    suspend fun getShipmentsAndDrivers(): RemoteShipmentsAndDrivers
}

fun ShipmentAndDriverService(): ShipmentAndDriverService {
    return DefaultShipmentsAndDriversService(jsonData)
}

class DefaultShipmentsAndDriversService(
    private val jsonString: String,
) : ShipmentAndDriverService {
    override suspend fun getShipmentsAndDrivers(): RemoteShipmentsAndDrivers {
        return Json.decodeFromString(jsonString)
    }
}

@Language("json")
private val jsonData =
    """
    {
        "shipments": [
            "215 Osinski Manors",
            "9856 Marvin Stravenue",
            "7127 Kathlyn Ferry",
            "987 Champlin Lake",
            "63187 Volkman Garden Suite 447",
            "75855 Dessie Lights",
            "1797 Adolf Island Apt. 744",
            "2431 Lindgren Corners",
            "8725 Aufderhar River Suite 859",
            "79035 Shanna Light Apt. 322"
        ],
        "drivers": [
          "Everardo Welch",
          "Orval Mayert",
          "Howard Emmerich",
          "Izaiah Lowe",
          "Monica Hermann",
          "Ellis Wisozk",
          "Noemie Murphy",
          "Cleve Durgan",
          "Murphy Mosciski",
          "Kaiser Sose"
        ]
    }
    """.trimIndent()