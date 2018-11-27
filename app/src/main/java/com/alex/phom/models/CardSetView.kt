package com.alex.phom.models

data class CardSetView(
        val cardList: List<Cardview>,
        val setInfo: SetInfoView,
        val version: Long
)

data class Cardview(
        val cardID: Long,
        val cardText: NameView,
        val baseCardID: Long,
        val CardType: CardTypeView,
        val cardName: NameView,
        val miniImage: ImageView,
        val largeImage: LargeImageView,
        val heroIngameImage: ImageView,
        val illustrator: String? = "",
        val rarity: RarityView? = null,
        val cardColor: CardColorView,
        val itemDef: Long? = null,
        val attack: Long? = null,
        val hitPoints: Long? = null,
        val references: List<Any>,
        val manaCost: Long? = null,
        val isCrosslane: Boolean? = null,
        val charges: Long? = null,
        val armor: Long? = null,
        val subType: SubTypeView? = null,
        val goldCost: Long? = null,
        val isQuick: Boolean? = null
)

data class ImageView(
        val default: String? = null
)

data class LargeImageView(
        val default: String? = null,
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

enum class CardColorView {
    BLACK,
    GREEN,
    BLUE,
    RED,
    UNKNOWN
}

enum class CardTypeView {
    ABILITY,
    CREEP,
    HERO,
    IMPROVEMENT,
    ITEM,
    PASSIVE_ABILITY,
    SPELL,
    UNKNOWN
}


enum class RarityView {
    COMMON,
    RARE,
    UNCOMMON,
    UNKNOWN
}

data class NameView(
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

enum class SubTypeView {
    ACCESSORY,
    ARMOR,
    CONSUMABLE,
    DEED,
    WEAPON,
    UNKNOWN
}

data class SetInfoView(
        val setID: Long,
        val packItemDef: Long,
        val name: NameView
)
