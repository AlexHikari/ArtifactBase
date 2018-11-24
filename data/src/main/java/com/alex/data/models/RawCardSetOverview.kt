package com.alex.data.models

import com.squareup.moshi.Json
import io.realm.annotations.PrimaryKey


data class RawCardSetOverview(
        @Json(name = "card_set")
        var cardSet: CardSet = CardSet()
)

data class CardSet(
        @Json(name = "card_list")
        var cardList: List<Card> = listOf(),
        @Json(name = "set_info")
        var setInfo: SetInfo = SetInfo(),
        @Json(name = "version")
        var version: Long = 0
)

data class SetInfo(
        @Json(name = "name")
        var name: Name = Name(),
        @Json(name = "pack_item_def")
        var packItemDef: Long = 0,
        @Json(name = "set_id")
        var setID: Long = 0
)

data class Name(
        @Json(name = "brazilian")
        var brazilian: String = "",
        @Json(name = "bulgarian")
        var bulgarian: String = "",
        @Json(name = "czech")
        var czech: String = "",
        @Json(name = "danish")
        var danish: String = "",
        @Json(name = "dutch")
        var dutch: String = "",
        @Json(name = "english")
        var english: String = "",
        @Json(name = "finnish")
        var finnish: String = "",
        @Json(name = "french")
        var french: String = "",
        @Json(name = "german")
        var german: String = "",
        @Json(name = "greek")
        var greek: String = "",
        @Json(name = "hungarian")
        var hungarian: String = "",
        @Json(name = "italian")
        var italian: String = "",
        @Json(name = "japanese")
        var japanese: String = "",
        @Json(name = "koreana")
        var koreana: String = "",
        @Json(name = "latam")
        var latam: String = "",
        @Json(name = "norwegian")
        var norwegian: String = "",
        @Json(name = "polish")
        var polish: String = "",
        @Json(name = "portuguese")
        var portuguese: String = "",
        @Json(name = "romanian")
        var romanian: String = "",
        @Json(name = "russian")
        var russian: String = "",
        @Json(name = "schinese")
        var schinese: String = "",
        @Json(name = "spanish")
        var spanish: String = "",
        @Json(name = "swedish")
        var swedish: String = "",
        @Json(name = "tchinese")
        var tchinese: String = "",
        @Json(name = "thai")
        var thai: String = "",
        @Json(name = "turkish")
        var turkish: String = "",
        @Json(name = "ukrainian")
        var ukrainian: String = "",
        @Json(name = "vietnamese")
        var vietnamese: String = ""
)

data class Card(
        @Json(name = "armor")
        var armor: Long = 0,
        @Json(name = "attack")
        var attack: Long = 0,
        @Json(name = "base_card_id")
        @PrimaryKey
        var baseCardId: Long = 0,
        @Json(name = "card_id")
        var cardId: Long = 0,
        @Json(name = "card_name")
        var cardName: CardName = CardName(),
        @Json(name = "card_text")
        var cardText: CardText = CardText(),
        @Json(name = "card_type")
        var cardType: String = "",
        @Json(name = "charges")
        var charges: Long = 0,
        @Json(name = "gold_cost")
        var goldCost: Long = 0,
        @Json(name = "hit_poLongs")
        var hitPoints: Long = 0,
        @Json(name = "illustrator")
        var illustrator: String = "",
        @Json(name = "ingame_image")
        var heroIngameImage: MiniImage = MiniImage(),
        @Json(name = "is_black")
        var isBlack: Boolean = false,
        @Json(name = "is_blue")
        var isBlue: Boolean = false,
        @Json(name = "is_crosslane")
        var isCrosslane: Boolean = false,
        @Json(name = "is_green")
        var isGreen: Boolean = false,
        @Json(name = "is_quick")
        var isQuick: Boolean = false,
        @Json(name = "is_red")
        var isRed: Boolean = false,
        @Json(name = "item_def")
        var itemDef: Long = 0,
        @Json(name = "large_image")
        var largeImage: LargeImage = LargeImage(),
        @Json(name = "mana_cost")
        var manaCost: Long = 0,
        @Json(name = "mini_image")
        var miniImage: MiniImage = MiniImage(),
        @Json(name = "rarity")
        var rarity: String = "",
        @Json(name = "references")
        var references: List<Any> = listOf(),
        @Json(name = "sub_type")
        var subType: String = ""
)


data class MiniImage(
        @Json(name = "default")
        var default: String = ""
)

data class LargeImage(
        @Json(name = "brazilian")
        var brazilian: String = "",
        @Json(name = "default")
        var default: String = "",
        @Json(name = "french")
        var french: String = "",
        @Json(name = "german")
        var german: String = "",
        @Json(name = "italian")
        var italian: String = "",
        @Json(name = "japanese")
        var japanese: String = "",
        @Json(name = "koreana")
        var koreana: String = "",
        @Json(name = "latam")
        var latam: String = "",
        @Json(name = "russian")
        var russian: String = "",
        @Json(name = "schinese")
        var schinese: String = "",
        @Json(name = "spanish")
        var spanish: String = "",
        @Json(name = "tchinese")
        var tchinese: String = ""
)

data class CardName(
        @Json(name = "brazilian")
        var brazilian: String = "",
        @Json(name = "bulgarian")
        var bulgarian: String = "",
        @Json(name = "czech")
        var czech: String = "",
        @Json(name = "danish")
        var danish: String = "",
        @Json(name = "dutch")
        var dutch: String = "",
        @Json(name = "english")
        var english: String = "",
        @Json(name = "finnish")
        var finnish: String = "",
        @Json(name = "french")
        var french: String = "",
        @Json(name = "german")
        var german: String = "",
        @Json(name = "greek")
        var greek: String = "",
        @Json(name = "hungarian")
        var hungarian: String = "",
        @Json(name = "italian")
        var italian: String = "",
        @Json(name = "japanese")
        var japanese: String = "",
        @Json(name = "koreana")
        var koreana: String = "",
        @Json(name = "latam")
        var latam: String = "",
        @Json(name = "norwegian")
        var norwegian: String = "",
        @Json(name = "polish")
        var polish: String = "",
        @Json(name = "portuguese")
        var portuguese: String = "",
        @Json(name = "romanian")
        var romanian: String = "",
        @Json(name = "russian")
        var russian: String = "",
        @Json(name = "schinese")
        var schinese: String = "",
        @Json(name = "spanish")
        var spanish: String = "",
        @Json(name = "swedish")
        var swedish: String = "",
        @Json(name = "tchinese")
        var tchinese: String = "",
        @Json(name = "thai")
        var thai: String = "",
        @Json(name = "turkish")
        var turkish: String = "",
        @Json(name = "ukrainian")
        var ukrainian: String = "",
        @Json(name = "vietnamese")
        var vietnamese: String = ""
)

data class CardText(
        @Json(name = "brazilian")
        var brazilian: String = "",
        @Json(name = "bulgarian")
        var bulgarian: String = "",
        @Json(name = "czech")
        var czech: String = "",
        @Json(name = "danish")
        var danish: String = "",
        @Json(name = "dutch")
        var dutch: String = "",
        @Json(name = "english")
        var english: String = "",
        @Json(name = "finnish")
        var finnish: String = "",
        @Json(name = "french")
        var french: String = "",
        @Json(name = "german")
        var german: String = "",
        @Json(name = "greek")
        var greek: String = "",
        @Json(name = "hungarian")
        var hungarian: String = "",
        @Json(name = "italian")
        var italian: String = "",
        @Json(name = "japanese")
        var japanese: String = "",
        @Json(name = "koreana")
        var koreana: String = "",
        @Json(name = "latam")
        var latam: String = "",
        @Json(name = "norwegian")
        var norwegian: String = "",
        @Json(name = "polish")
        var polish: String = "",
        @Json(name = "portuguese")
        var portuguese: String = "",
        @Json(name = "romanian")
        var romanian: String = "",
        @Json(name = "russian")
        var russian: String = "",
        @Json(name = "schinese")
        var schinese: String = "",
        @Json(name = "spanish")
        var spanish: String = "",
        @Json(name = "swedish")
        var swedish: String = "",
        @Json(name = "tchinese")
        var tchinese: String = "",
        @Json(name = "thai")
        var thai: String = "",
        @Json(name = "turkish")
        var turkish: String = "",
        @Json(name = "ukrainian")
        var ukrainian: String = "",
        @Json(name = "vietnamese")
        var vietnamese: String = ""
)