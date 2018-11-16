package com.alex.data.utils

import android.util.Log
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


    val bytes: MutableList<Byte> = encodeBytes(deckContent)
    val deckCode: String = encodeBytesToString(bytes)

    return deckCode
}


fun encodeBytesToString(bytes: MutableList<Byte>): String {

    val encoded: String = android.util.Base64.encodeToString(bytes.toByteArray(), android.util.Base64.DEFAULT)
    var deckString: String = ENCODED_PREFIX + encoded

    deckString = deckString.replace('/', '-')
    deckString = deckString.replace('=', '_')

    return deckString
}


private fun encodeBytes(deckContent: Deck): MutableList<Byte> {

    deckContent.heroes.sortedBy { it.id }
    deckContent.cards.sortedBy { it.id }

    val countHeroes: Int = deckContent.heroes.count()
    val allCards: List<Card> = deckContent.heroes + deckContent.cards

    val bytes: MutableList<Byte> = mutableListOf()
    // Version and hero count
    val version: UInt = CURRENT_VERSION shl 4 or extractNBitsWithCarry(countHeroes, 3)
    addByte(bytes, version)


    // Checksum is updated at the end
    val dummyCheckSum: UInt = 0U
    val checksumByte: Int = bytes.count()
    addByte(bytes, dummyCheckSum)

    // Namesize
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
    addByte(bytes, nameLen)
    addRemainingNumberToBuffer(countHeroes, 3, bytes)

    // card offset
    var prevCardId = 0
    // with hero cards
    for (currHero in 0 until countHeroes) {
        val card: Hero = allCards[currHero].toHero()
        addCardToBuffer(card.turn.toUInt(), card.id - prevCardId, bytes)
        prevCardId = card.id
    }

    // reset card offset
    prevCardId = 0

    // With all the cards
    for (currCard in countHeroes until allCards.count()) {
        val card: Card = allCards[currCard]

        addCardToBuffer(card.count.toUInt(), card.id - prevCardId, bytes)
        prevCardId = card.id
    }

    // save off the pre string bytes for the checksum
    val preStringByteCount: Int = bytes.count()

    //write the string
    val nameBytes: ByteArray = name.toByteArray(Charsets.UTF_8)
    nameBytes.forEach { nameByte -> addByte(bytes, nameByte.toUInt()) }

    val unFullCheckSum: UInt = computeChecksum(bytes, preStringByteCount - HEADER_SIZE)
    val unSmallChecksum: UInt = unFullCheckSum and 0x0FFU

    bytes[checksumByte] = unSmallChecksum.toByte()
    return bytes
}

fun extractNBitsWithCarry(value: Int, numBits: Int): UInt {

    val limitBit: UInt = 1U shl numBits
    var result: UInt = (value.toUInt() and (limitBit - 1U))
    if (value >= limitBit.toInt()) {
        result = result or limitBit
    }

    return result
}

fun addByte(bytes: MutableList<Byte>, b: UInt) {

    if (b > 255U) {
        Log.i("error value", "unvalid value")
    }
    bytes.add(b.toByte())
}

/**
 * Utility to write the rest of a number into a buffer. This will first strip the specified N bits off, and then write a series of bytes of the structure of 1 overflow bit and 7 data bits
 * @param value Int
 * @param alreadyWrittenBits Int
 * @param bytes MutableList<Byte>
 */
fun addRemainingNumberToBuffer(value: Int, alreadyWrittenBits: Int, bytes: MutableList<Byte>) {

    var valueCopy: Int = value shr alreadyWrittenBits
    var numBytes: Int = 0
    while (valueCopy > 0) {
        val nextByte: UInt = extractNBitsWithCarry(valueCopy, 7)
        valueCopy = valueCopy shr 7
        addByte(bytes, nextByte)
        numBytes++
    }
}

fun addCardToBuffer(count: UInt, value: Int, bytes: MutableList<Byte>) {


    val countBytesStart: Int = bytes.count()

    //determine our count. We can only store 2 bits, and we know the value is at least one, so we can encode values 1-5. However, we set both bits to indicate an
    //extended count encoding
    val firstByteMaxCount: UInt = 0x03U
    val extendedCount: Boolean = (count - 1U) >= firstByteMaxCount

    //determine our first byte, which contains our count, a continue flag, and the first few bits of our value
    val firstByteCount: UInt = if (extendedCount) firstByteMaxCount else (count - 1U)
    var firstByte: UInt = (firstByteCount shl 6)

    firstByte = firstByte or extractNBitsWithCarry(value, 5)
    addByte(bytes, firstByte)

    //now continue writing out the rest of the number with a carry flag
    addRemainingNumberToBuffer(value, 5, bytes)

    //now if we overflowed on the count, encode the remaining count
    if (extendedCount) {
        addRemainingNumberToBuffer(count.toInt(), 0, bytes)
    }
    val countBytesEnd: Int = bytes.count()
    if (countBytesEnd - countBytesStart > 11) {
        Log.i("error", "something whent horribly wrong")
    }
}

fun computeChecksum(bytes: MutableList<Byte>, numBytes: Int): UInt {
    var checksum: UInt = 0U
    for (addCheck in HEADER_SIZE until (numBytes + HEADER_SIZE)) {
        val b: Byte = bytes[addCheck]
        checksum = checksum + b.toUInt()
    }

    return checksum
}
