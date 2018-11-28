package com.alex.data.models

data class CardSetDAO(

        var card_set: CardSetInfoDAO = CardSetInfoDAO()
)

data class CardSetInfoDAO(

        var card_list: List<CardDAO> = listOf(),
        var set_info: SetInfoDAO = SetInfoDAO(),
        var version: Long = 0
)

data class SetInfoDAO(

        var name: NameDAO = NameDAO(),
        var pack_item_def: Long = 0,
        var set_id: Long = 0
)

data class NameDAO(

        var brazilian: String? = "",
        var bulgarian: String? = "",
        var czech: String? = "",
        var danish: String? = "",
        var dutch: String? = "",
        var english: String? = "",
        var finnish: String? = "",
        var french: String? = "",
        var german: String? = "",
        var greek: String? = "",
        var hungarian: String? = "",
        var italian: String? = "",
        var japanese: String? = "",
        var koreana: String? = "",
        var latam: String? = "",
        var norwegian: String? = "",
        var polish: String? = "",
        var portuguese: String? = "",
        var romanian: String? = "",
        var russian: String? = "",
        var schinese: String? = "",
        var spanish: String? = "",
        var swedish: String? = "",
        var tchinese: String? = "",
        var thai: String? = "",
        var turkish: String? = "",
        var ukrainian: String? = "",
        var vietnamese: String? = ""
)

data class CardDAO(

        var armor: Long = 0,
        var attack: Long? = 0,
        var base_card_id: Long = 0,
        var card_id: Long = 0,
        var card_name: CardNameDAO = CardNameDAO(),
        var card_text: CardTextDAO = CardTextDAO(),
        var card_type: String = "",
        var charges: Long = 0,
        var gold_cost: Long = 0,
        var hit_points: Long? = 0,
        var illustrator: String? = "",
        var ingame_image: MiniImageDAO = MiniImageDAO(),
        var is_black: Boolean? = false,
        var is_blue: Boolean? = false,
        var is_crosslane: Boolean? = false,
        var is_green: Boolean? = false,
        var is_quick: Boolean? = false,
        var is_red: Boolean? = false,
        var item_def: Long? = 0,
        var large_image: LargeImageDAO = LargeImageDAO(),
        var mana_cost: Long? = 0,
        var mini_image: MiniImageDAO = MiniImageDAO(),
        var rarity: String? = "",
        var references: List<ReferenceDAO> = listOf(),
        var sub_type: String? = ""
)

data class ReferenceDAO(
        open var card_id: Long? = 0,
        open var ref_type: String? = "",
        open var count: Long? = 0

)


data class MiniImageDAO(

        var default: String? = ""
)

data class LargeImageDAO(

        var brazilian: String? = "",
        var default: String? = "",
        var french: String? = "",
        var german: String? = "",
        var italian: String? = "",
        var japanese: String? = "",
        var koreana: String? = "",
        var latam: String? = "",
        var russian: String? = "",
        var schinese: String? = "",
        var spanish: String? = "",
        var tchinese: String? = ""
)

data class CardNameDAO(

        var brazilian: String? = "",
        var bulgarian: String? = "",
        var czech: String? = "",
        var danish: String? = "",
        var dutch: String? = "",
        var english: String? = "",
        var finnish: String? = "",
        var french: String? = "",
        var german: String? = "",
        var greek: String? = "",
        var hungarian: String? = "",
        var italian: String? = "",
        var japanese: String? = "",
        var koreana: String? = "",
        var latam: String? = "",
        var norwegian: String? = "",
        var polish: String? = "",
        var portuguese: String? = "",
        var romanian: String? = "",
        var russian: String? = "",
        var schinese: String? = "",
        var spanish: String? = "",
        var swedish: String? = "",
        var tchinese: String? = "",
        var thai: String? = "",
        var turkish: String? = "",
        var ukrainian: String? = "",
        var vietnamese: String? = ""
)

data class CardTextDAO(

        var brazilian: String? = "",
        var bulgarian: String? = "",
        var czech: String? = "",
        var danish: String? = "",
        var dutch: String? = "",
        var english: String? = "",
        var finnish: String? = "",
        var french: String? = "",
        var german: String? = "",
        var greek: String? = "",
        var hungarian: String? = "",
        var italian: String? = "",
        var japanese: String? = "",
        var koreana: String? = "",
        var latam: String? = "",
        var norwegian: String? = "",
        var polish: String? = "",
        var portuguese: String? = "",
        var romanian: String? = "",
        var russian: String? = "",
        var schinese: String? = "",
        var spanish: String? = "",
        var swedish: String? = "",
        var tchinese: String? = "",
        var thai: String? = "",
        var turkish: String? = "",
        var ukrainian: String? = "",
        var vietnamese: String? = ""
)

