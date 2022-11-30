package com.jonfhancock.acmeincrouting.domain.model

data class Shipment(
    val addressNumber: String,
    val addressStreet: String,
    val unitType: String?,
    val unitNumber: String?
) {
    //TODO Assumption: Street Name length includes any spaces
    val streetNameIsEven = addressStreet.length % 2 == 0
    val streetNameLengthFactors: List<Int> = addressStreet.length.let { l ->
        when {
            // Since we do not want to match on 1 as a common factor,
            // we treat a length of one as having an empty list of factors
            l <= 1 -> emptyList()
            else -> {
                // We skip 1 because all whole numbers include 1 as a factor,
                // and our business rules prohibit using 1 as a matching factor
                // Take integers 2 through half the length of the street name
                // Only keep the integers that, when divided by the length, result in a whole number
                // Add the length itself to the end of the list
                (2..l / 2).filter { l % it == 0 }.plus(l)
            }
        }

    }
    private val unitDisplay = unitType?.let { " $it" }?.plus(unitNumber?.let { " $it" }) ?: ""
    val displayName = "$addressNumber $addressStreet$unitDisplay"

}

fun Shipment(destination: String): Shipment {
    // Split raw destination into parts separated by spaces
    // Trim beginning and ending white space from each part
    // And filter out any parts that are only white space
    val parts = destination.split(" ").map { it.trim() }.filter { it.isNotBlank() }.toMutableList()

    // Take the first part, and if it is numeric, use it as the number for the street address
    // Then remove it from the parts deque
    val number = parts.firstOrNull()?.takeIf { numericOnlyRegex.matches(it) }?.also {
        parts.removeFirst()
    } ?: ""

    // Next, take all parts until we get to a unit type e.g. Apt. or Suite., or to the end of the list
    // And remove them from the parts list so we can process the unit type and number if applicable
    // Then recombine these parts to form the street name.
    val street = parts.takeWhile { part -> !unitTypes.any { part.equals(it, ignoreCase = true) } }
        .also { streetParts ->
            if (streetParts.isNotEmpty()) {
                repeat(streetParts.size) { parts.removeFirst() }
            }
        }.joinToString(separator = " ")

    // If there are any elements left in the parts list, they must be a unit type.
    // Pop the first element from the deque, and use it as the unit type.
    val unitType = parts.removeFirstOrNull()

    // Once unit type is removed, the only remaining element (if any) will be the unit number.
    val unitNumber = parts.removeFirstOrNull()
    return Shipment(
        number, street, unitType, unitNumber
    )
}

private val unitTypes = listOf("Apt.", "Suite", "Unit", "Ste.")
private val numericOnlyRegex = "^[0-9]+$".toRegex()
