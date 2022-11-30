package com.jonfhancock.acmeincrouting.domain.model

import com.jonfhancock.acmeincrouting.nChars
import org.junit.Assert
import org.junit.Test

internal class ShipmentTest {

    @Test
    fun `test destination parsing with empty and blank strings`() {
        Assert.assertEquals(
            Shipment("", "", null, null), Shipment(destination = "")
        )
        Assert.assertEquals(
            Shipment("", "", null, null), Shipment(destination = " ")
        )
        Assert.assertEquals(
            Shipment("", "", null, null), Shipment(destination = "\t")
        )
        Assert.assertEquals(
            Shipment("", "", null, null), Shipment(destination = "\n")
        )
    }

    @Test
    fun `test destination parsing with no street number`() {
        Assert.assertEquals(
            Shipment("", "Street Name", "Apt.", "1"), Shipment(destination = "Street Name Apt. 1")
        )
        Assert.assertEquals(
            Shipment("", "Street Name", "Suite", "100"),
            Shipment(destination = "Street Name Suite 100")
        )
        Assert.assertEquals(
            Shipment("", "Street Name", null, null), Shipment(destination = "Street Name")
        )
    }

    @Test
    fun `test destination parsing with full address`() {
        Assert.assertEquals(
            Shipment("123", "Street Name", "Apt.", "1"),
            Shipment(destination = "123 Street Name Apt. 1")
        )
        Assert.assertEquals(
            Shipment("123", "Street Name", "Suite", "100"),
            Shipment(destination = "123 Street Name Suite 100")
        )
        Assert.assertEquals(
            Shipment("123", "Street Name", null, null), Shipment(destination = "123 Street Name")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", null, null),
            Shipment(destination = "123 Very Extremely Long Street Name")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Apt.", "1"),
            Shipment(destination = "123 Very Extremely Long Street Name Apt. 1")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = "123 Very Extremely Long Street Name Suite 100")
        )

    }

    @Test
    fun `test destination parsing with extra white space`() {
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = " 123 Very Extremely Long Street Name Suite 100 ")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = "\t123 Very Extremely Long Street Name Suite 100\t")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = "\n123 Very Extremely Long Street Name Suite 100\n")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = "123  Very  Extremely  Long  Street  Name  Suite  100")
        )
        Assert.assertEquals(
            Shipment("123", "Very Extremely Long Street Name", "Suite", "100"),
            Shipment(destination = "123 \tVery\t Extremely \t Long Street Name Suite 100")
        )
    }

    @Test
    fun `test is street name length even`() {
        // Odd Street names
        Assert.assertFalse(Shipment(destination = "a").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "abc").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "a a").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "a aa aa").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "aa aa a").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "aa a aa").streetNameIsEven)
        Assert.assertFalse(Shipment(destination = "aa ab").streetNameIsEven)

        // Even Street Names
        Assert.assertTrue(Shipment(destination = "").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "zz").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "zzxxyy").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "zz z").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "z zz").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "zz zzz").streetNameIsEven)
        Assert.assertTrue(Shipment(destination = "zz zz zz").streetNameIsEven)
    }

    @Test
    fun `test name length factors`() {
        Assert.assertEquals(emptyList<Int>(), Shipment(nChars(0)).streetNameLengthFactors)
        Assert.assertEquals(emptyList<Int>(), Shipment(nChars(1)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2), Shipment(nChars(2)).streetNameLengthFactors)
        Assert.assertEquals(listOf(3), Shipment(nChars(3)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2, 4), Shipment(nChars(4)).streetNameLengthFactors)
        Assert.assertEquals(listOf(5), Shipment(nChars(5)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2, 3, 6), Shipment(nChars(6)).streetNameLengthFactors)
        Assert.assertEquals(listOf(7), Shipment(nChars(7)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2, 4, 8), Shipment(nChars(8)).streetNameLengthFactors)
        Assert.assertEquals(listOf(3, 9), Shipment(nChars(9)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2, 5, 10), Shipment(nChars(10)).streetNameLengthFactors)
        Assert.assertEquals(listOf(11), Shipment(nChars(11)).streetNameLengthFactors)
        Assert.assertEquals(listOf(2, 3, 4, 6, 12), Shipment(nChars(12)).streetNameLengthFactors)
    }
}