package com.alex.data.models.mapper

import com.alex.data.models.*
import com.alex.domain.models.*
import io.realm.RealmList

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
        references = this.references.toReferenceList(),
        subType = this.sub_type?.toSubType()


)

fun List<ReferenceDAO>.toReferenceList(): List<Reference> {

    val references = mutableListOf<Reference>()

    this.forEach { reference ->
        references.add(reference.toReference())
    }
    return references
}

fun ReferenceDAO.toReference(): Reference = Reference(
        card_id = this.card_id!!,
        ref_type = this.ref_type!!,
        count = this.count!!
)

fun LargeImageDAO.toLargeImage(): LargeImage = LargeImage(
        imgdef = this.default,
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
        img = this.default
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
        if (is_black!!)
            color = CardColor.BLACK
    if (is_blue != null)
        if (is_blue!!)
            color = CardColor.BLUE
    if (is_green != null)
        if (is_green!!)
            color = CardColor.GREEN
    if (is_red != null)
        if (is_red!!)
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
        references = this.references.toReferenceDAOList(),
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

fun List<Reference>.toReferenceDAOList(): List<ReferenceDAO> {
    val references = mutableListOf<ReferenceDAO>()
    this.forEach { reference ->
        reference.toReferenceDAO()
    }
    return references
}

fun Reference.toReferenceDAO(): ReferenceDAO = ReferenceDAO(
        card_id = this.card_id,
        count = this.count,
        ref_type = this.ref_type
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
        default = this.img
)

fun LargeImage.toLargeImageDAO(): LargeImageDAO = LargeImageDAO(
        brazilian = this.brazilian,
        default = this.imgdef,
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

fun CardSetVo.toCardSet(): CardSet = CardSet(
        cardList = this.card_list.toCardList(),
        setInfo = this.set_info.toSetInfo(),
        version = this.version
)

fun RealmList<CardVo>.toCardList(): List<Card> {
    return this.toList().map { return@map it.toCard() }
}

fun SetInfoVo.toSetInfo(): SetInfo = SetInfo(
        name = this.name.toName(),
        packItemDef = this.pack_item_def,
        setID = this.set_id
)

fun NameVo.toName(): Name = Name(
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
        bulgarian = this.bulgarian
)

fun CardVo.toCard(): Card = Card(
        cardID = this.card_id,
        subType = this.sub_type.toSubType(),
        references = this.references.toListOfReference(),
        rarity = this.rarity.toRarity(),
        miniImage = this.mini_image.toImage(),
        manaCost = this.mana_cost,
        largeImage = this.large_image.toLargeImage(),
        itemDef = this.item_def,
        isQuick = this.is_quick,
        isCrosslane = this.is_crosslane,
        illustrator = this.illustrator,
        hitPoints = this.hit_points,
        heroIngameImage = this.ingame_image.toImage(),
        goldCost = this.gold_cost,
        charges = this.charges,
        cardText = this.card_text.toName(),
        cardName = this.card_name.toName(),
        cardColor = this.toCardColor(),
        attack = this.attack,
        armor = this.armor,
        cardType = this.card_type.toCardType(),
        baseCardID = this.base_card_id
)

fun RealmList<ReferenceVo>.toListOfReference(): List<Reference> {
    return this.toList().map { return@map it.toReference() }
}

fun ReferenceVo.toReference(): Reference = Reference(
        card_id = this.card_id,
        ref_type = this.ref_type,
        count = this.count
)

fun MiniImageVo.toImage(): Image = Image(
        img = this.img
)

fun LargeImageVo.toLargeImage(): LargeImage = LargeImage(
        imgdef = this.imgdef,
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

fun CardTextVo.toName(): Name = Name(
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
        bulgarian = this.bulgarian
)

fun CardNameVo.toName(): Name = Name(
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
        bulgarian = this.bulgarian
)

fun CardVo.toCardColor(): CardColor {

    var color: CardColor = CardColor.UNKNOWN
    if (is_black)
        color = CardColor.BLACK
    if (is_blue)
        color = CardColor.BLUE
    if (is_green)
        color = CardColor.GREEN
    if (is_red)
        color = CardColor.RED
    return color
}

fun CardSet.toCardSetVo(setId: Long): CardSetVo = CardSetVo(
        card_list = this.cardList.toRealmList(),
        version = this.version,
        set_info = this.setInfo.toSetInfoVo(),
        set_id = setId
)

fun SetInfo.toSetInfoVo(): SetInfoVo = SetInfoVo(
        name = this.name.toNameVo(),
        pack_item_def = this.packItemDef,
        set_id = this.setID
)

fun Name.toNameVo(): NameVo = NameVo(
        english = this.english!!,
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
        brazilian = this.brazilian!!,
        vietnamese = this.vietnamese!!,
        ukrainian = this.ukrainian!!,
        turkish = this.turkish!!,
        thai = this.thai!!,
        swedish = this.swedish!!,
        romanian = this.romanian!!,
        portuguese = this.portuguese!!,
        polish = this.polish!!,
        norwegian = this.norwegian!!,
        hungarian = this.hungarian!!,
        greek = this.greek!!,
        finnish = this.finnish!!,
        dutch = this.dutch!!,
        danish = this.danish!!,
        czech = this.czech!!,
        bulgarian = this.bulgarian!!
)

fun List<Card>.toRealmList(): RealmList<CardVo> {
    val cards = RealmList<CardVo>()
    this.forEach {
        cards.add(it.toCardVo())
    }
    return cards
}

fun Card.toCardVo(): CardVo = CardVo(
        base_card_id = this.baseCardID,
        armor = this.armor!!,
        attack = this.attack!!,
        charges = this.charges!!,
        illustrator = this.illustrator!!,
        rarity = this.rarity.toString().toLowerCase().replace("_", " "),
        references = this.references.toRealmListReferences(),
        card_id = this.cardID,
        card_name = this.cardName.toCardNameVo(),
        card_text = this.cardText.toCardTextVo(),
        card_type = this.cardType.toString().toLowerCase().replace("_", " "),
        gold_cost = this.goldCost!!,
        hit_points = this.hitPoints!!,
        ingame_image = this.heroIngameImage.toMiniImageVo(),
        is_black = this.cardColor == CardColor.BLACK,
        is_blue = this.cardColor == CardColor.BLUE,
        is_crosslane = this.isCrosslane!!,
        is_green = this.cardColor == CardColor.GREEN,
        is_quick = this.isQuick!!,
        is_red = this.cardColor == CardColor.RED,
        item_def = this.itemDef!!,
        large_image = this.largeImage.toLargeImageVo(),
        mana_cost = this.manaCost!!,
        mini_image = this.miniImage.toMiniImageVo(),
        sub_type = this.subType.toString().toLowerCase().replace("_", " ")
)

fun LargeImage.toLargeImageVo(): LargeImageVo = LargeImageVo(
        brazilian = this.brazilian!!,
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
        imgdef = this.imgdef!!
)

fun Image.toMiniImageVo(): MiniImageVo = MiniImageVo(
        img = this.img!!
)

fun Name.toCardTextVo(): CardTextVo = CardTextVo(
        brazilian = this.brazilian!!,
        bulgarian = this.bulgarian!!,
        czech = this.czech!!,
        danish = this.danish!!,
        dutch = this.dutch!!,
        finnish = this.finnish!!,
        greek = this.greek!!,
        hungarian = this.hungarian!!,
        norwegian = this.norwegian!!,
        polish = this.polish!!,
        romanian = this.romanian!!,
        swedish = this.swedish!!,
        thai = this.thai!!,
        turkish = this.turkish!!,
        ukrainian = this.ukrainian!!,
        vietnamese = this.vietnamese!!,
        french = this.french!!,
        german = this.german!!,
        italian = this.italian!!,
        japanese = this.japanese!!,
        koreana = this.koreana!!,
        latam = this.latam!!,
        russian = this.russian!!,
        schinese = this.schinese!!,
        spanish = this.spanish!!,
        tchinese = this.tchinese!!,
        portuguese = this.portuguese!!,
        english = this.english!!
)

fun Name.toCardNameVo(): CardNameVo = CardNameVo(
        english = this.english!!,
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
        brazilian = this.brazilian!!,
        vietnamese = this.vietnamese!!,
        ukrainian = this.ukrainian!!,
        turkish = this.turkish!!,
        thai = this.thai!!,
        swedish = this.swedish!!,
        romanian = this.romanian!!,
        portuguese = this.portuguese!!,
        polish = this.polish!!,
        norwegian = this.norwegian!!,
        hungarian = this.hungarian!!,
        greek = this.greek!!,
        finnish = this.finnish!!,
        dutch = this.dutch!!,
        danish = this.danish!!,
        czech = this.czech!!,
        bulgarian = this.bulgarian!!
)

fun List<Reference>.toRealmListReferences(): RealmList<ReferenceVo> {

    val references = RealmList<ReferenceVo>()
    this.forEach { reference ->
        references.add(reference.toReferenceVo())
    }
    return references
}


fun Reference.toReferenceVo(): ReferenceVo = ReferenceVo(
        card_id = this.card_id,
        count = this.count,
        ref_type = this.ref_type
)

