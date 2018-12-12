package com.alex.domain.models

data class CardSet(
        val cardList: List<Card>,
        val setInfo: SetInfo,
        val version: Long
)

data class Card(
        val cardID: Long,
        val baseCardID: Long,
        val cardType: CardType,
        val cardName: Name,
        val cardText: Name,
        val miniImage: Image,
        val largeImage: LargeImage,
        val heroIngameImage: Image,
        val illustrator: String? = null,
        val rarity: Rarity? = null,
        val cardColor: CardColor,
        val itemDef: Long? = null,
        val attack: Long? = null,
        val hitPoints: Long? = null,
        val references: List<Reference>,
        val manaCost: Long? = null,
        val isCrosslane: Boolean? = null,
        val charges: Long? = null,
        val armor: Long? = null,
        val subType: SubType? = null,
        val goldCost: Long? = null,
        val isQuick: Boolean? = null
)

data class Reference(
        var card_id: Long = 0,
        var ref_type: String = "",
        var count: Long = 0

)

data class Name(
        val english: String? = null,
        val german: String? = null,
        val french: String? = null,
        val italian: String? = null,
        val koreana: String? = null,
        val spanish: String? = null,
        val schinese: String? = null,
        val tchinese: String? = null,
        val russian: String? = null,
        val thai: String? = null,
        val japanese: String? = null,
        val portuguese: String? = null,
        val polish: String? = null,
        val danish: String? = null,
        val dutch: String? = null,
        val finnish: String? = null,
        val norwegian: String? = null,
        val swedish: String? = null,
        val hungarian: String? = null,
        val czech: String? = null,
        val romanian: String? = null,
        val turkish: String? = null,
        val brazilian: String? = null,
        val bulgarian: String? = null,
        val greek: String? = null,
        val ukrainian: String? = null,
        val latam: String? = null,
        val vietnamese: String? = null
)

enum class CardColor {
    BLACK,
    GREEN,
    BLUE,
    RED,
    UNKNOWN
}

enum class CardType {
    ABILITY,
    CREEP,
    HERO,
    IMPROVEMENT,
    ITEM,
    PASSIVE_ABILITY,
    SPELL,
    UNKNOWN
}

data class Image(
        val img: String? = null
)

data class LargeImage(
        val imgdef: String? = null,
        val german: String? = null,
        val french: String? = null,
        val italian: String? = null,
        val koreana: String? = null,
        val spanish: String? = null,
        val schinese: String? = null,
        val tchinese: String? = null,
        val russian: String? = null,
        val japanese: String? = null,
        val brazilian: String? = null,
        val latam: String? = null
)

enum class Rarity {
    COMMON,
    RARE,
    UNCOMMON,
    UNKNOWN
}


enum class SubType {
    ACCESSORY,
    ARMOR,
    CONSUMABLE,
    DEED,
    WEAPON,
    UNKNOWN
}

data class SetInfo(
        val setID: Long,
        val packItemDef: Long,
        val name: Name
)
