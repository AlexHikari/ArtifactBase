package com.alex.phom.models.mappers

import com.alex.domain.models.*
import com.alex.phom.models.*

fun CardSet.toCardSetView(): CardSetView = CardSetView(
        cardList = this.cardList.toCardListView(),
        setInfo = this.setInfo.toSetInfoView(),
        version = this.version
)

fun List<Card>.toCardListView(): List<Cardview> {
    val list = mutableListOf<Cardview>()
    this.forEach {
        list.add(it.toCardView())
    }
    return list
}

fun Card.toCardView(): Cardview = Cardview(
        baseCardID = this.baseCardID,
        cardText = this.cardText.toNameView(),
        armor = this.armor,
        attack = this.attack,
        cardColor = this.cardColor.toCardColorView(),
        cardID = this.cardID,
        cardName = this.cardName.toNameView(),
        CardType = this.cardType.toTypeView(),
        charges = this.charges,
        goldCost = this.goldCost,
        heroIngameImage = this.heroIngameImage.toImageView(),
        hitPoints = this.hitPoints,
        illustrator = this.illustrator,
        isCrosslane = this.isCrosslane,
        isQuick = this.isQuick,
        itemDef = this.itemDef,
        largeImage = this.largeImage.toLargeImageView(),
        manaCost = this.manaCost,
        miniImage = this.miniImage.toImageView(),
        rarity = this.rarity?.toRarityView(),
        references = this.references,
        subType = this.subType?.toSubTypeView()
)

fun CardColor.toCardColorView(): CardColorView {
    return when (this) {
        CardColor.BLACK -> CardColorView.BLACK
        CardColor.BLUE -> CardColorView.BLUE
        CardColor.GREEN -> CardColorView.GREEN
        CardColor.RED -> CardColorView.RED
        else -> CardColorView.UNKNOWN
    }
}

fun CardType.toTypeView(): CardTypeView {
    return when (this) {
        CardType.ABILITY -> CardTypeView.ABILITY
        CardType.CREEP -> CardTypeView.CREEP
        CardType.HERO -> CardTypeView.HERO
        CardType.IMPROVEMENT -> CardTypeView.IMPROVEMENT
        CardType.ITEM -> CardTypeView.ITEM
        CardType.PASSIVE_ABILITY -> CardTypeView.PASSIVE_ABILITY
        CardType.SPELL -> CardTypeView.SPELL
        else -> CardTypeView.UNKNOWN
    }
}

fun Rarity.toRarityView(): RarityView {
    return when (this) {
        Rarity.COMMON -> RarityView.COMMON
        Rarity.RARE -> RarityView.RARE
        Rarity.UNCOMMON -> RarityView.UNKNOWN
        else -> RarityView.UNKNOWN
    }
}

fun SubType.toSubTypeView(): SubTypeView {
    return when (this) {
        SubType.ACCESSORY -> SubTypeView.ACCESSORY
        SubType.ARMOR -> SubTypeView.ARMOR
        SubType.CONSUMABLE -> SubTypeView.CONSUMABLE
        SubType.DEED -> SubTypeView.DEED
        SubType.WEAPON -> SubTypeView.WEAPON
        else -> SubTypeView.UNKNOWN
    }
}

fun Image.toImageView(): ImageView = ImageView(
        default = this.default
)

fun Name.toNameView(): NameView = NameView(
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

fun LargeImage.toLargeImageView(): LargeImageView = LargeImageView(
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
        french = this.french,
        brazilian = this.brazilian
)

fun SetInfo.toSetInfoView(): SetInfoView = SetInfoView(
        setID = this.setID,
        name = this.name.toNameView(),
        packItemDef = this.packItemDef
)