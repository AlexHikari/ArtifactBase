package com.alex.phom.models

data class CardSetView(
        val cardList: List<CardView>,
        val setInfo: SetInfoView,
        val version: Long
)

data class CardView(
        val cardID: Long,
        val cardText: NameView,
        val baseCardID: Long,
        val CardType: CardTypeView,
        val cardName: NameView,
        val miniImage: ImageView,
        val largeImage: LargeImageView,
        val heroInGameImage: ImageView,
        val illustrator: String = "",
        val rarity: RarityView = RarityView.UNKNOWN,
        val cardColor: CardColorView,
        val itemDef: Long = 0,
        val attack: Long = 0,
        val hitPoints: Long = 0,
        val references: List<Any>,
        val manaCost: Long = 0,
        val isCrossLane: Boolean = false,
        val charges: Long = 0,
        val armor: Long = 0,
        val subType: SubTypeView = SubTypeView.UNKNOWN,
        val goldCost: Long = 0,
        val isQuick: Boolean = false
)

data class ImageView(
        val img: String = ""
)

data class LargeImageView(
        val imgDef: String = "",
        val german: String = "",
        val french: String = "",
        val italian: String = "",
        val koreana: String = "",
        val spanish: String = "",
        val schinese: String = "",
        val tchinese: String = "",
        val russian: String = "",
        val japanese: String = "",
        val brazilian: String = "",
        val latam: String = ""
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
        val english: String = "",
        val german: String = "",
        val french: String = "",
        val italian: String = "",
        val koreana: String = "",
        val spanish: String = "",
        val schinese: String = "",
        val tchinese: String = "",
        val russian: String = "",
        val thai: String = "",
        val japanese: String = "",
        val portuguese: String = "",
        val polish: String = "",
        val danish: String = "",
        val dutch: String = "",
        val finnish: String = "",
        val norwegian: String = "",
        val swedish: String = "",
        val hungarian: String = "",
        val czech: String = "",
        val romanian: String = "",
        val turkish: String = "",
        val brazilian: String = "",
        val bulgarian: String = "",
        val greek: String = "",
        val ukrainian: String = "",
        val latam: String = "",
        val vietnamese: String = ""
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
        val setID: Long = 0,
        val packItemDef: Long = 0,
        val name: NameView
)
