package com.alex.data.models.mapper

import com.alex.data.models.*
import com.alex.domain.models.*

fun CardSetDAO.toCardSet(): CardSet = CardSet(
        cardList = this.cardSet.cardList.toCards(),
        setInfo = this.cardSet.setInfo.toCardSetInfo(),
        version = this.cardSet.version
)

fun List<CardDAO>.toCards(): List<Card> {
    val list = mutableListOf<Card>()
    this.forEach {
        list.add(it.toCard())
    }
    return list
}

fun SetInfoDAO.toCardSetInfo(): SetInfo = SetInfo(
        setID = this.setID,
        name = this.name.toName(),
        packItemDef = this.packItemDef
)

fun NameDAO.toName(): Name = Name(
        english = this.english,
        tchinese = this.tchinese,
        spanish = this.spanish,
        schinese = this.schinese,
        russian = this.russian,
        latam = this.latam,
        koreana = this.koreana,
        japanese = this.japanese,
        italian = this.italian,
        german = this.german,
        french = this.french,
        brazilian = this.brazilian,
        bulgarian = this.bulgarian,
        czech = this.czech,
        danish = this.danish,
        dutch = this.dutch,
        finnish = this.finnish,
        greek = this.greek,
        hungarian = this.hungarian,
        norwegian = this.norwegian,
        polish = this.polish,
        portuguese = this.portuguese,
        romanian = this.romanian,
        swedish = this.swedish,
        thai = this.thai,
        turkish = this.turkish,
        ukrainian = this.ukrainian,
        vietnamese = this.vietnamese
)

fun CardDAO.toCard(): Card = Card(
        cardID = this.cardId,
        baseCardID = this.baseCardId,
        cardType = this.cardType.toCardType(),
        armor = this.armor,
        attack = this.attack,
        cardColor = this.toCardColor(),
        cardName = this.cardName.toCardName(),
        cardText = this.cardText.toCardText(),
        charges = this.charges,
        goldCost = this.goldCost,
        heroIngameImage = this.heroIngameImage.toImage(),
        hitPoints = this.hitPoints,
        illustrator = this.illustrator,
        isCrosslane = this.isCrosslane,
        isQuick = this.isQuick,
        itemDef = this.itemDef,
        largeImage = this.largeImage.toLargeImage(),
        manaCost = this.manaCost,
        miniImage = this.miniImage.toImage(),
        rarity = this.rarity.toRarity(),
        references = this.references,
        subType = this.subType.toSubType()


)

fun LargeImageDAO.toLargeImage(): LargeImage = LargeImage(
        default = this.default,
        brazilian = this.brazilian,
        french = this.french,
        german = this.german,
        italian = this.italian,
        japanese = this.japanese,
        koreana = this.koreana,
        latam = this.latam,
        russian = this.russian,
        schinese = this.schinese,
        spanish = this.spanish,
        tchinese = this.tchinese
)

fun MiniImageDAO.toImage(): Image = Image(
        default = this.default
)

fun CardTextDAO.toCardText(): Name = Name(
        english = this.english,
        vietnamese = this.vietnamese,
        ukrainian = this.ukrainian,
        turkish = this.turkish,
        thai = this.thai,
        tchinese = this.tchinese,
        swedish = this.swedish,
        spanish = this.spanish,
        schinese = this.schinese,
        russian = this.russian,
        romanian = this.romanian,
        portuguese = this.portuguese,
        polish = this.polish,
        norwegian = this.norwegian,
        latam = this.latam,
        koreana = this.koreana,
        japanese = this.japanese,
        italian = this.italian,
        hungarian = this.hungarian,
        greek = this.greek,
        german = this.german,
        french = this.french,
        finnish = this.finnish,
        dutch = this.dutch,
        danish = this.danish,
        czech = this.czech,
        bulgarian = this.bulgarian,
        brazilian = this.brazilian
)

fun CardNameDAO.toCardName(): Name = Name(
        english = this.english,
        brazilian = this.brazilian,
        bulgarian = this.bulgarian,
        czech = this.czech,
        danish = this.danish,
        dutch = this.dutch,
        finnish = this.finnish,
        french = this.french,
        german = this.german,
        greek = this.greek,
        hungarian = this.hungarian,
        italian = this.italian,
        japanese = this.japanese,
        koreana = this.koreana,
        latam = this.latam,
        norwegian = this.norwegian,
        polish = this.polish,
        portuguese = this.portuguese,
        romanian = this.romanian,
        russian = this.russian,
        schinese = this.schinese,
        spanish = this.spanish,
        swedish = this.swedish,
        tchinese = this.tchinese,
        thai = this.thai,
        turkish = this.turkish,
        ukrainian = this.ukrainian,
        vietnamese = this.vietnamese
)

fun CardDAO.toCardColor(): CardColor {

    var color: CardColor = CardColor.UNKNOWN
    if (isBlack)
        color = CardColor.BLACK
    if (isBlue)
        color = CardColor.BLUE
    if (isGreen)
        color = CardColor.GREEN
    if (isRed)
        color = CardColor.RED
    return color
}

fun String.toRarity(): Rarity {
    return when (this) {
        "Common" -> Rarity.COMMON
        "Rare" -> Rarity.RARE
        "Uncommon" -> Rarity.UNCOMMON
        else -> Rarity.UNKNOWN
    }
}

fun String.toCardType(): CardType {
    return when (this) {
        "Ability" -> CardType.ABILITY
        "Creep" -> CardType.CREEP
        "Hero" -> CardType.HERO
        "Improvement" -> CardType.IMPROVEMENT
        "Item" -> CardType.ITEM
        "Passive Ability" -> CardType.PASSIVE_ABILITY
        "Spell" -> CardType.SPELL
        else -> CardType.UNKNOWN
    }
}

fun String.toSubType(): SubType {
    return when (this) {
        "Accessory" -> SubType.ACCESSORY
        "Armor" -> SubType.ARMOR
        "Consumable" -> SubType.CONSUMABLE
        "Deed" -> SubType.DEED
        else -> SubType.UNKNOWN
    }
}