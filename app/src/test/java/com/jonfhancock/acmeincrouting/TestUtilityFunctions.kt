package com.jonfhancock.acmeincrouting

fun nChars(n: Int = 0, char: Char = 'a'): String {
    return char.toString().repeat(n)
}