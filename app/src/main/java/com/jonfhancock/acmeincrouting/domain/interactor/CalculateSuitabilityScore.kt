package com.jonfhancock.acmeincrouting.domain.interactor

import com.jonfhancock.acmeincrouting.domain.model.Driver
import com.jonfhancock.acmeincrouting.domain.model.Shipment


const val EvenStreetNameScoreMultiplier = 1.5f
const val OddStreetNameScoreMultiplier = 1f
const val SharedFactorsBonusMultiplier = .5f

class CalculateSuitabilityScore(
    private val evenStreetNameScoreMultiplier: Float = EvenStreetNameScoreMultiplier,
    private val oddStreetNameScoreMultiplier: Float = OddStreetNameScoreMultiplier,
    private val sharedFactorsBonusMultiplier: Float = SharedFactorsBonusMultiplier
) {
    operator fun invoke(shipment: Shipment, driver: Driver): Float {
        val baseScore = if (shipment.streetNameIsEven) {
            driver.vowelCount * evenStreetNameScoreMultiplier
        } else {
            driver.consonantCount * oddStreetNameScoreMultiplier
        }
        val sharedFactors =
            driver.nameLengthFactors.intersect(shipment.streetNameLengthFactors.toSet())
        val bonus = if (sharedFactors.isEmpty()) 0f else baseScore * sharedFactorsBonusMultiplier
        return baseScore + bonus
    }
}