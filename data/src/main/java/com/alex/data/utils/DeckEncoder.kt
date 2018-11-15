package com.alex.data.utils

import kotlin.math.floor

private const val CURRENT_VERSION: UInt = 2U
private const val ENCODED_PREFIX = "ADC"
private const val MAX_BYTES_FOR_VAR_UINT_32 = 5
private const val HEADER_SIZE = 3


/**
 * Mock Deck model to test the class seen that card should be the father of heroes data class
 * @property heroes List<Hero> list of heroes
 * @property cards List<Card> list of cards
 * @property name String name of the deck
 * @constructor
 */
data class Deck(val heroes: List<Card>, val cards: List<Card>, val name: String)

data class Card(
        val id: Int,
        val count: Int,
        val turn: Int?
)

data class Hero(
        val id: Int,
        val count: Int,
        val turn: Int
)

private fun Card.toHero(): Hero {

    return Hero(id = this.id, count = this.count, turn = this.turn!!)

}


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
    val version: UInt = CURRENT_VERSION shl 4 or extractNBitsWithCarry(countHeroes, 3)
    AddByte(bytes, version)

    val dummyCheckSum: UInt = 0U
    val checksumByte: Int = bytes.count()
    AddByte(bytes, dummyCheckSum)

    var nameLen: UInt = 0U
    var name: String = deckContent.name
    var trimLen: Int = name.length
    while (trimLen > 63) {
        var amountToTrim: Int = floor((trimLen.toDouble() - 63) / 4).toInt()
        amountToTrim = if (amountToTrim > 1) amountToTrim else 1
        name = name.substring(0, name.length - amountToTrim)
        trimLen = name.length
    }
    nameLen = name.length.toUInt()
    AddByte(bytes, nameLen)
    addRemainingNumberToBuffer(countHeroes, 3, bytes)

    var prevCardId = 0
    for (currHero in 0..countHeroes) {
        val card: Hero = allCards[currHero].toHero()
        addCardToBuffer(card.turn.toUInt(), card.id - prevCardId, bytes)
        prevCardId = card.id
    }

    prevCardId = 0

    for (currCard in countHeroes..allCards.count()) {
        val card: Card = allCards[currCard]

        AddCardToBuffer(card.count.toUInt(), card.id - prevCardId, bytes)
        prevCardId = card.id
    }
    

}


fun extractNBitsWithCarry(countHeroes: Int, i: Int): UInt {

}
