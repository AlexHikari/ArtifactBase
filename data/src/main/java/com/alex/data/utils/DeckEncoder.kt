package com.alex.data.utils

private const val CURRENT_VERSION = 2
private const val ENCODED_PREFIX = "ADC"
private const val MAX_BYTES_FOR_VAR_UINT_32 = 5
private const val HEADER_SIZE = 3


/**
 * Mock Deck model to test the class
 * @property heroes List<Hero> list of heroes
 * @property cards List<Card> list of cards
 * @property name String name of the deck
 * @constructor
 */
data class Deck(val heroes: List<Card>, val cards: List<Card>, val name: String)

data class Card(
        val id: Int,
        val count: Int
)


//https://github.com/MisterJimson/ArtifactDeckCodeDotNet/blob/master/ArtifactDeckCodeDotNet/ArtifactDeckEncoder.cs

/**
 * Encodes the given deck null checks are shorted out in the presentation domain
 * @param deckContent Deck
 * @return String Encoded value of the deck
 */
fun encodeDeck(deckContent: Deck): String {


    val bytes: List<Byte> = encodeBytes(deckContent)
    val deckCode: String = encodeBytesToString(bytes)

    return deckCode
}

private fun encodeBytesToString(bytes: List<Byte>): String {

}

private fun encodeBytes(deckContent: Deck): List<Byte> {

    deckContent.heroes.sortedBy { it.id }
    deckContent.cards.sortedBy { it.id }

    val countHeroes: Int = deckContent.heroes.count()
    val allCards: List<Card> = deckContent.heroes + deckContent.cards

    val bytes: List<Byte> = listOf()
    val version: UInt = CURRENT_VERSION shl 4 or AddByte(bytes, version)

    val dummyCheckSum: Int
}

fun ExtractNBitsWithCarry(countHeroes: Int, i: Int): UInt {

}
