package com.jonfhancock.acmeincrouting.domain.model

import java.text.Normalizer

data class Driver(
    val name: String
) {
    private val normalizedName = Normalizer
        // Split letters with diacritics into separate unicode characters
        .normalize(name, Normalizer.Form.NFD)
        // Remove the diacritics, leaving behind only undecorated letters
        .replace(nonSpacingCharactersRegex, "")

    //TODO Assumption: Driver name length includes any spaces
    val nameLengthFactors = name.length.let { l ->
        when {
            // Since we do not want to match on 1 as a common factor,
            // we treat a length of one as having an empty list of factors
            l <= 1 -> emptyList()
            else -> {
                // We skip 1 because all whole numbers include 1 as a factor,
                // and our business rules prohibit using 1 as a matching factor
                // Take integers 2 through half the length of the name
                // Only keep the integers that, when divided by the length, result in a whole number
                // Add the length itself to the end of the list
                (2..l / 2).filter { l % it == 0 }.plus(l)
            }
        }
    }


    val vowelCount = normalizedName.count { vowels.contains(it, ignoreCase = true) }
    val consonantCount = normalizedName.count { consonants.contains(it, ignoreCase = true) }
}

//TODO Assumption: Y is treated as a vowel for this case
private const val vowels = "aeæioœøuy"
private const val consonants = "bcdfghjklłmnpqrsßtvwxz"
private val nonSpacingCharactersRegex = "\\p{Mn}".toRegex()
