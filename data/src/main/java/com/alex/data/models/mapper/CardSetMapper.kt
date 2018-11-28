package com.alex.data.models.mapper

import com.alex.data.models.*
import com.alex.domain.models.*

fun CardSetDAO.toCardSet(): CardSet = CardSet(
        cardList = this.card_set.card_list.toCards(),
        setInfo = this.card_set.set_info.toCardSetInfo(),
        version = this.card_set.version
)

fun List<CardDAO>.toCards(): List<Card> {
    val list = mutableListOf<Card>()
    this.forEach {
        list.add(it.toCard())
    }
    return list
}

fun SetInfoDAO.toCardSetInfo(): SetInfo = SetInfo(
        setID = this.set_id,
        name = this.name.toName(),
        packItemDef = this.pack_item_def
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
        cardID = this.card_id,
        baseCardID = this.base_card_id,
        cardType = this.card_type.toCardType(),
        armor = this.armor,
        attack = this.attack,
        cardColor = this.toCardColor(),
        cardName = this.card_name.toCardName(),
        cardText = this.card_text.toCardText(),
        charges = this.charges,
        goldCost = this.gold_cost,
        heroIngameImage = this.ingame_image.toImage(),
        hitPoints = this.hit_points,
        illustrator = this.illustrator,
        isCrosslane = this.is_crosslane,
        isQuick = this.is_quick,
        itemDef = this.item_def,
        largeImage = this.large_image.toLargeImage(),
        manaCost = this.mana_cost,
        miniImage = this.mini_image.toImage(),
        rarity = this.rarity?.toRarity(),
        references = this.references,
        subType = this.sub_type?.toSubType()


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
    if (is_black != null)
        color = CardColor.BLACK
    if (is_blue != null)
        color = CardColor.BLUE
    if (is_green != null)
        color = CardColor.GREEN
    if (is_red != null)
        color = CardColor.RED
    return color
}

fun String.toRarity(): Rarity {
    return when (this) {
        "Common" -> Rarity.COMMON
        "common" -> Rarity.COMMON
        "Rare" -> Rarity.RARE
        "rare" -> Rarity.RARE
        "Uncommon" -> Rarity.UNCOMMON
        "uncommon" -> Rarity.UNCOMMON
        else -> Rarity.UNKNOWN
    }
}

fun String.toCardType(): CardType {
    return when (this) {
        "Ability" -> CardType.ABILITY
        "ability" -> CardType.ABILITY
        "Creep" -> CardType.CREEP
        "creep" -> CardType.CREEP
        "Hero" -> CardType.HERO
        "hero" -> CardType.HERO
        "Improvement" -> CardType.IMPROVEMENT
        "improvement" -> CardType.IMPROVEMENT
        "Item" -> CardType.ITEM
        "item" -> CardType.ITEM
        "Passive Ability" -> CardType.PASSIVE_ABILITY
        "passive ability" -> CardType.PASSIVE_ABILITY
        "Spell" -> CardType.SPELL
        "spell" -> CardType.SPELL
        else -> CardType.UNKNOWN
    }
}

fun String.toSubType(): SubType {
    return when (this) {
        "Accessory" -> SubType.ACCESSORY
        "accessory" -> SubType.ACCESSORY
        "Armor" -> SubType.ARMOR
        "armor" -> SubType.ARMOR
        "Consumable" -> SubType.CONSUMABLE
        "consumable" -> SubType.CONSUMABLE
        "Deed" -> SubType.DEED
        "deed" -> SubType.DEED
        else -> SubType.UNKNOWN
    }
}

fun CardSet.toCardSetDAO(): CardSetDAO = CardSetDAO(
        card_set = CardSetInfoDAO(
                card_list = this.cardList.toCardsDAO(),
                version = this.version,
                set_info = this.setInfo.toSetInfoDAO()
        )
)

fun List<Card>.toCardsDAO(): List<CardDAO> {

    val cardList = mutableListOf<CardDAO>()
    this.forEach { card ->
        cardList.add(card.toCardDAO())
    }

    return cardList
}

fun Card.toCardDAO(): CardDAO = CardDAO(
        base_card_id = this.baseCardID,
        references = this.references,
        rarity = this.rarity.toString().toLowerCase().replace("_", " "),
        illustrator = this.illustrator,
        charges = this.charges!!,
        attack = this.attack,
        armor = this.armor!!,
        card_id = this.cardID,
        card_name = this.cardName.toCardNameDAO(),
        card_text = this.cardText.toCardTextDAO(),
        card_type = this.cardType.toString().toLowerCase().replace("_", " "),
        gold_cost = this.goldCost!!,
        hit_points = this.hitPoints,
        ingame_image = this.heroIngameImage.toMiniImageDAO(),
        is_black = this.cardColor == CardColor.BLACK,
        is_blue = this.cardColor == CardColor.BLUE,
        is_crosslane = this.isCrosslane,
        is_green = this.cardColor == CardColor.GREEN,
        is_quick = this.isQuick,
        is_red = this.cardColor == CardColor.RED,
        item_def = this.itemDef,
        large_image = this.largeImage.toLargeImageDAO(),
        mana_cost = this.manaCost,
        mini_image = this.miniImage.toMiniImageDAO(),
        sub_type = this.subType.toString().toLowerCase().replace("_", " ")
)


fun Name.toCardNameDAO(): CardNameDAO = CardNameDAO(
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
        tchinese = this.tchinese,
        vietnamese = this.vietnamese,
        ukrainian = this.ukrainian,
        turkish = this.turkish,
        thai = this.thai,
        swedish = this.swedish,
        romanian = this.romanian,
        portuguese = this.portuguese,
        polish = this.polish,
        norwegian = this.norwegian,
        hungarian = this.hungarian,
        greek = this.greek,
        finnish = this.finnish,
        dutch = this.dutch,
        danish = this.danish,
        czech = this.czech,
        bulgarian = this.bulgarian,
        english = this.english
)

fun Name.toCardTextDAO(): CardTextDAO = CardTextDAO(
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
        tchinese = this.tchinese,
        vietnamese = this.vietnamese,
        ukrainian = this.ukrainian,
        turkish = this.turkish,
        thai = this.thai,
        swedish = this.swedish,
        romanian = this.romanian,
        portuguese = this.portuguese,
        polish = this.polish,
        norwegian = this.norwegian,
        hungarian = this.hungarian,
        greek = this.greek,
        finnish = this.finnish,
        dutch = this.dutch,
        danish = this.danish,
        czech = this.czech,
        bulgarian = this.bulgarian,
        english = this.english
)

fun Image.toMiniImageDAO(): MiniImageDAO = MiniImageDAO(
        default = this.default
)

fun LargeImage.toLargeImageDAO(): LargeImageDAO = LargeImageDAO(
        brazilian = this.brazilian,
        default = this.default,
        tchinese = this.tchinese,
        spanish = this.spanish,
        schinese = this.schinese,
        russian = this.russian,
        latam = this.latam,
        koreana = this.koreana,
        japanese = this.japanese,
        italian = this.italian,
        german = this.german,
        french = this.french
)

fun SetInfo.toSetInfoDAO(): SetInfoDAO = SetInfoDAO(
        name = this.name.toNameDAO(),
        pack_item_def = this.packItemDef,
        set_id = this.setID
)

fun Name.toNameDAO(): NameDAO = NameDAO(
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
        tchinese = this.tchinese,
        vietnamese = this.vietnamese,
        ukrainian = this.ukrainian,
        turkish = this.turkish,
        thai = this.thai,
        swedish = this.swedish,
        romanian = this.romanian,
        portuguese = this.portuguese,
        polish = this.polish,
        norwegian = this.norwegian,
        hungarian = this.hungarian,
        greek = this.greek,
        finnish = this.finnish,
        dutch = this.dutch,
        danish = this.danish,
        czech = this.czech,
        bulgarian = this.bulgarian,
        english = this.english
)
