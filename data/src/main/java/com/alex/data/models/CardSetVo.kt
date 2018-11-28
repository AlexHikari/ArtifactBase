package com.alex.data.models

import io.realm.RealmList
import io.realm.RealmObject


open class CardSetInfoVo(

        var card_list: RealmList<CardVo> = RealmList(),

        var set_info: SetInfoVo = SetInfoVo(),

        var version: Long = 0
) : RealmObject()

open class SetInfoVo(

        var name: NameVo = NameVo(),

        var pack_item_def: Long = 0,

        var set_id: Long = 0
) : RealmObject()

open class NameVo(

        var brazilian: String = "",

        var bulgarian: String = "",

        var czech: String = "",

        var danish: String = "",

        var dutch: String = "",

        var english: String = "",

        var finnish: String = "",

        var french: String = "",

        var german: String = "",

        var greek: String = "",

        var hungarian: String = "",

        var italian: String = "",

        var japanese: String = "",

        var koreana: String = "",

        var latam: String = "",

        var norwegian: String = "",

        var polish: String = "",

        var portuguese: String = "",

        var romanian: String = "",

        var russian: String = "",

        var schinese: String = "",

        var spanish: String = "",

        var swedish: String = "",

        var tchinese: String = "",

        var thai: String = "",

        var turkish: String = "",

        var ukrainian: String = "",

        var vietnamese: String = ""
) : RealmObject()

open class CardVo(

        var armor: Long = 0,

        var attack: Long = 0,

        var base_card_id: Long = 0,

        var card_id: Long = 0,

        var card_name: CardNameVo = CardNameVo(),

        var card_text: CardTextVo = CardTextVo(),

        var card_type: String = "",

        var charges: Long = 0,

        var gold_cost: Long = 0,

        var hit_points: Long = 0,

        var illustrator: String = "",

        var ingame_image: MiniImageVo = MiniImageVo(),

        var is_black: Boolean = false,

        var is_blue: Boolean = false,

        var is_crosslane: Boolean = false,

        var is_green: Boolean = false,

        var is_quick: Boolean = false,

        var is_red: Boolean = false,

        var item_def: Long = 0,

        var large_image: LargeImageVo = LargeImageVo(),

        var mana_cost: Long = 0,

        var mini_image: MiniImageVo = MiniImageVo(),

        var rarity: String = "",

        var references: RealmList<Any> = RealmList(),

        var sub_type: String = ""
) : RealmObject()


open class MiniImageVo(
        var defauldt: String = ""
) : RealmObject()

open class LargeImageVo(

        var brazilian: String = "",

        var default: String = "",

        var french: String = "",

        var german: String = "",

        var italian: String = "",

        var japanese: String = "",

        var koreana: String = "",

        var latam: String = "",

        var russian: String = "",

        var schinese: String = "",

        var spanish: String = "",

        var tchinese: String = ""
) : RealmObject()

open class CardNameVo(

        var brazilian: String = "",

        var bulgarian: String = "",

        var czech: String = "",

        var danish: String = "",

        var dutch: String = "",

        var english: String = "",

        var finnish: String = "",

        var french: String = "",

        var german: String = "",

        var greek: String = "",

        var hungarian: String = "",

        var italian: String = "",

        var japanese: String = "",

        var koreana: String = "",

        var latam: String = "",

        var norwegian: String = "",

        var polish: String = "",

        var portuguese: String = "",

        var romanian: String = "",

        var russian: String = "",

        var schinese: String = "",

        var spanish: String = "",

        var swedish: String = "",

        var tchinese: String = "",

        var thai: String = "",

        var turkish: String = "",

        var ukrainian: String = "",

        var vietnamese: String = ""
) : RealmObject()

open class CardTextVo(

        var brazilian: String = "",

        var bulgarian: String = "",

        var czech: String = "",

        var danish: String = "",

        var dutch: String = "",

        var english: String = "",

        var finnish: String = "",

        var french: String = "",

        var german: String = "",

        var greek: String = "",

        var hungarian: String = "",

        var italian: String = "",

        var japanese: String = "",

        var koreana: String = "",

        var latam: String = "",

        var norwegian: String = "",

        var polish: String = "",

        var portuguese: String = "",

        var romanian: String = "",

        var russian: String = "",

        var schinese: String = "",

        var spanish: String = "",

        var swedish: String = "",

        var tchinese: String = "",

        var thai: String = "",

        var turkish: String = "",

        var ukrainian: String = "",

        var vietnamese: String = ""
) : RealmObject()

