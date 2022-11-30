package com.jonfhancock.acmeincrouting.domain.interactor

import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment
import com.jonfhancock.acmeincrouting.nChars
import org.junit.Assert
import org.junit.Test

internal class CalculateSuitabilityScoreTest {

    private val calculateSuitabilityScore = CalculateSuitabilityScore(
        evenStreetNameScoreMultiplier = 1.5f,
        oddStreetNameScoreMultiplier = 1f,
        sharedFactorsBonusMultiplier = .5f
    )

    @Test
    fun `test even street name scores`() {
        val shipment = Shipment(nChars(2))
        Assert.assertEquals(
            1.5f, calculateSuitabilityScore(shipment, Driver("a"))
        )
        Assert.assertEquals(
            0f, calculateSuitabilityScore(shipment, Driver("b"))
        )
        Assert.assertEquals(
            2.25f, calculateSuitabilityScore(shipment, Driver("ab"))
        )
        Assert.assertEquals(
            4.5f, calculateSuitabilityScore(shipment, Driver("aa"))
        )
        Assert.assertEquals(
            0f, calculateSuitabilityScore(shipment, Driver("bb"))
        )
        Assert.assertEquals(
            2.25f, calculateSuitabilityScore(shipment, Driver("ba"))
        )
    }

    @Test
    fun `test odd street name scores`() {
        val shipment = Shipment(nChars(3))
        Assert.assertEquals(
            0f, calculateSuitabilityScore(shipment, Driver("a"))
        )
        Assert.assertEquals(
            1f, calculateSuitabilityScore(shipment, Driver("b"))
        )
        Assert.assertEquals(
            1.5f, calculateSuitabilityScore(shipment, Driver("aba"))
        )
        Assert.assertEquals(
            3f, calculateSuitabilityScore(shipment, Driver("abb"))
        )
        Assert.assertEquals(
            4.5f, calculateSuitabilityScore(shipment, Driver("bbb"))
        )
        Assert.assertEquals(
            1.5f, calculateSuitabilityScore(shipment, Driver("baa"))
        )
    }
}