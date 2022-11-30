package com.jonfhancock.acmeincrouting.domain.model

import com.jonfhancock.acmeincrouting.nChars
import org.junit.Assert
import org.junit.Test


internal class DriverTest {

    @Test
    fun `test vowel count`() {
        Assert.assertEquals(18, Driver("aàáâäæãåāAÀÁÂÄÆÃÅĀ").vowelCount)
        Assert.assertEquals(16, Driver("eèéêëēėęEÈÉÊËĒĖĘ").vowelCount)
        Assert.assertEquals(14, Driver("iîïíīįìIÎÏÍĪĮÌ").vowelCount)
        Assert.assertEquals(18, Driver("oôöòóœøōõOÔÖÒÓŒØŌÕ").vowelCount)
        Assert.assertEquals(12, Driver("uûüùúūUÛÜÙÚŪ").vowelCount)
        Assert.assertEquals(4, Driver("yÿYŸ").vowelCount)
        Assert.assertEquals(0, Driver("    !@#\$%^&*()-=_+[]{}\\|;:'\",<.>/?").vowelCount)
        Assert.assertEquals(0, Driver("").vowelCount)
        Assert.assertEquals(0, Driver("BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvXxZz").vowelCount)
        Assert.assertEquals(0, Driver("ñłßž").vowelCount)
        Assert.assertEquals(0, Driver("cçćčCÇĆČ").vowelCount)
        Assert.assertEquals(0, Driver("lłLŁ").vowelCount)
        Assert.assertEquals(0, Driver("nñńNÑŃ").vowelCount)
        Assert.assertEquals(0, Driver("sßśšSŚŠ").vowelCount)
        Assert.assertEquals(0, Driver("zžźżZŽŹŻ").vowelCount)
    }

    @Test
    fun `test consonant count`() {
        Assert.assertEquals(0, Driver("aàáâäæãåāAÀÁÂÄÆÃÅĀ").consonantCount)
        Assert.assertEquals(0, Driver("eèéêëēėęEÈÉÊËĒĖĘ").consonantCount)
        Assert.assertEquals(0, Driver("iîïíīįìIÎÏÍĪĮÌ").consonantCount)
        Assert.assertEquals(0, Driver("oôöòóœøōõOÔÖÒÓŒØŌÕ").consonantCount)
        Assert.assertEquals(0, Driver("uûüùúūUÛÜÙÚŪ").consonantCount)
        Assert.assertEquals(0, Driver("yÿYŸ").consonantCount)
        Assert.assertEquals(0, Driver("    !@#\$%^&*()-=_+[]{}\\|;:'\",<.>/?").consonantCount)
        Assert.assertEquals(0, Driver("").consonantCount)
        Assert.assertEquals(38, Driver("BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvXxZz").consonantCount)
        Assert.assertEquals(8, Driver("cçćčCÇĆČ").consonantCount)
        Assert.assertEquals(4, Driver("lłLŁ").consonantCount)
        Assert.assertEquals(6, Driver("nñńNÑŃ").consonantCount)
        Assert.assertEquals(7, Driver("sßśšSŚŠ").consonantCount)
        Assert.assertEquals(8, Driver("zžźżZŽŹŻ").consonantCount)

    }

    @Test
    fun `test name length factors`() {
        Assert.assertEquals(emptyList<Int>(), Driver(nChars(0)).nameLengthFactors)
        Assert.assertEquals(emptyList<Int>(), Driver(nChars(1)).nameLengthFactors)
        Assert.assertEquals(listOf(2), Driver(nChars(2)).nameLengthFactors)
        Assert.assertEquals(listOf(3), Driver(nChars(3)).nameLengthFactors)
        Assert.assertEquals(listOf(2, 4), Driver(nChars(4)).nameLengthFactors)
        Assert.assertEquals(listOf(5), Driver(nChars(5)).nameLengthFactors)
        Assert.assertEquals(listOf(2, 3, 6), Driver(nChars(6)).nameLengthFactors)
        Assert.assertEquals(listOf(7), Driver(nChars(7)).nameLengthFactors)
        Assert.assertEquals(listOf(2, 4, 8), Driver(nChars(8)).nameLengthFactors)
        Assert.assertEquals(listOf(3, 9), Driver(nChars(9)).nameLengthFactors)
        Assert.assertEquals(listOf(2, 5, 10), Driver(nChars(10)).nameLengthFactors)
        Assert.assertEquals(listOf(11), Driver(nChars(11)).nameLengthFactors)
        Assert.assertEquals(listOf(2, 3, 4, 6, 12), Driver(nChars(12)).nameLengthFactors)
    }

}