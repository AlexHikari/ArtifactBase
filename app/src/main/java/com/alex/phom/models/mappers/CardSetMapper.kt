package com.alex.phom.models.mappers

import com.alex.domain.models.*
import com.alex.phom.models.*

fun CardSet.toCardSetView(): CardSetView = CardSetView(
        cardList = this.cardList.toCardListView(),
        setInfo = this.setInfo.toSetInfoView(),
        version = this.version
)

fun List<Card>.toCardListView(): List<CardView> {
    val list = mutableListOf<CardView>()
    this.forEach {
        list.add(it.toCardView())
    }
    return list
}

fun Card.toCardView(): CardView = CardView(
        baseCardID = this.baseCardID,
        cardText = this.cardText.toNameView(),
        armor = this.armor!!,
        attack = this.attack!!,
        cardColor = this.cardColor.toCardColorView(),
        cardID = this.cardID,
        cardName = this.cardName.toNameView(),
        CardType = this.cardType.toTypeView(),
        charges = this.charges!!,
        goldCost = this.goldCost!!,
        heroInGameImage = this.heroIngameImage.toImageView(),
        hitPoints = this.hitPoints!!,
        illustrator = this.illustrator!!,
        isCrossLane = this.isCrosslane!!,
        isQuick = this.isQuick!!,
        itemDef = this.itemDef!!,
        largeImage = this.largeImage.toLargeImageView(),
        manaCost = this.manaCost!!,
        miniImage = this.miniImage.toImageView(),
        rarity = this.rarity!!.toRarityView(),
        references = this.references.toReferenceListView(),
        subType = this.subType!!.toSubTypeView()
)

fun List<Reference>.toReferenceListView(): List<ReferenceView> {
    val list = mutableListOf<ReferenceView>()
    this.forEach {
        list.add(it.toReferenceView())
    }
    return list
}

fun Reference.toReferenceView(): ReferenceView = ReferenceView(
        card_id = this.card_id,
        count = this.count,
        ref_type = this.ref_type
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
        img = this.img!!
)

fun Name.toNameView(): NameView = NameView(
        english = this.english!!,
        brazilian = this.brazilian!!,
        bulgarian = this.bulgarian!!,
        czech = this.czech!!,
        danish = this.danish!!,
        dutch = this.dutch!!,
        finnish = this.finnish!!,
        french = this.french!!,
        german = this.german!!,
        greek = this.greek!!,
        hungarian = this.hungarian!!,
        italian = this.italian!!,
        japanese = this.japanese!!,
        koreana = this.koreana!!,
        latam = this.latam!!,
        norwegian = this.norwegian!!,
        polish = this.polish!!,
        portuguese = this.portuguese!!,
        romanian = this.romanian!!,
        russian = this.russian!!,
        schinese = this.schinese!!,
        spanish = this.spanish!!,
        swedish = this.swedish!!,
        tchinese = this.tchinese!!,
        thai = this.thai!!,
        turkish = this.turkish!!,
        ukrainian = this.ukrainian!!,
        vietnamese = this.vietnamese!!
)

fun LargeImage.toLargeImageView(): LargeImageView = LargeImageView(
        imgDef = this.imgdef!!,
        tchinese = this.tchinese!!,
        spanish = this.spanish!!,
        schinese = this.schinese!!,
        russian = this.russian!!,
        latam = this.latam!!,
        koreana = this.koreana!!,
        japanese = this.japanese!!,
        italian = this.italian!!,
        german = this.german!!,
        french = this.french!!,
        brazilian = this.brazilian!!
)

fun SetInfo.toSetInfoView(): SetInfoView = SetInfoView(
        setID = this.setID,
        name = this.name.toNameView(),
        packItemDef = this.packItemDef
)