package com.alex.data.models

import io.realm.RealmList
import io.realm.RealmObject


open class CardVo(

        open var armor: Long = 0,
        open var attack: Long = 0,
        open var base_card_id: Long = 0,
        open var card_id: Long = 0,
        open var card_name: CardNameVo = CardNameVo(),
        open var card_text: CardTextVo = CardTextVo(),
        open var card_type: String = "",
        open var charges: Long = 0,
        open var gold_cost: Long = 0,
        open var hit_points: Long = 0,
        open var illustrator: String = "",
        open var ingame_image: MiniImageVo = MiniImageVo(),
        open var is_black: Boolean = false,
        open var is_blue: Boolean = false,
        open var is_crosslane: Boolean = false,
        open var is_green: Boolean = false,
        open var is_quick: Boolean = false,
        open var is_red: Boolean = false,
        open var item_def: Long = 0,
        open var large_image: LargeImageVo = LargeImageVo(),
        open var mana_cost: Long = 0,
        open var mini_image: MiniImageVo = MiniImageVo(),
        open var rarity: String = "",
        open var references: RealmList<ReferenceVo> = RealmList(ReferenceVo()),
        open var sub_type: String = ""
) : RealmObject()

open class ReferenceVo(
        open var card_id: Long = 0,
        open var ref_type: String = "",
        open var count: Long = 0

) : RealmObject()

open class CardSetVo(

        open var card_list: RealmList<CardVo> = RealmList(CardVo()),
        open var set_info: SetInfoVo = SetInfoVo(),
        open var version: Long = 0
) : RealmObject()

open class SetInfoVo(

        open var name: NameVo = NameVo(),
        open var pack_item_def: Long = 0,
        open var set_id: Long = 0
) : RealmObject()

open class NameVo(

        open var brazilian: String = "",
        open var bulgarian: String = "",
        open var czech: String = "",
        open var danish: String = "",
        open var dutch: String = "",
        open var english: String = "",
        open var finnish: String = "",
        open var french: String = "",
        open var german: String = "",
        open var greek: String = "",
        open var hungarian: String = "",
        open var italian: String = "",
        open var japanese: String = "",
        open var koreana: String = "",
        open var latam: String = "",
        open var norwegian: String = "",
        open var polish: String = "",
        open var portuguese: String = "",
        open var romanian: String = "",
        open var russian: String = "",
        open var schinese: String = "",
        open var spanish: String = "",
        open var swedish: String = "",
        open var tchinese: String = "",
        open var thai: String = "",
        open var turkish: String = "",
        open var ukrainian: String = "",
        open var vietnamese: String = ""
) : RealmObject()


open class MiniImageVo(
        open var defauldt: String = ""
) : RealmObject()

open class LargeImageVo(

        open var brazilian: String = "",
        open var default: String = "",
        open var french: String = "",
        open var german: String = "",
        open var italian: String = "",
        open var japanese: String = "",
        open var koreana: String = "",
        open var latam: String = "",
        open var russian: String = "",
        open var schinese: String = "",
        open var spanish: String = "",
        open var tchinese: String = ""
) : RealmObject()

open class CardNameVo(

        open var brazilian: String = "",
        open var bulgarian: String = "",
        open var czech: String = "",
        open var danish: String = "",
        open var dutch: String = "",
        open var english: String = "",
        open var finnish: String = "",
        open var french: String = "",
        open var german: String = "",
        open var greek: String = "",
        open var hungarian: String = "",
        open var italian: String = "",
        open var japanese: String = "",
        open var koreana: String = "",
        open var latam: String = "",
        open var norwegian: String = "",
        open var polish: String = "",
        open var portuguese: String = "",
        open var romanian: String = "",
        open var russian: String = "",
        open var schinese: String = "",
        open var spanish: String = "",
        open var swedish: String = "",
        open var tchinese: String = "",
        open var thai: String = "",
        open var turkish: String = "",
        open var ukrainian: String = "",
        open var vietnamese: String = ""
) : RealmObject()

open class CardTextVo(

        open var brazilian: String = "",
        open var bulgarian: String = "",
        open var czech: String = "",
        open var danish: String = "",
        open var dutch: String = "",
        open var english: String = "",
        open var finnish: String = "",
        open var french: String = "",
        open var german: String = "",
        open var greek: String = "",
        open var hungarian: String = "",
        open var italian: String = "",
        open var japanese: String = "",
        open var koreana: String = "",
        open var latam: String = "",
        open var norwegian: String = "",
        open var polish: String = "",
        open var portuguese: String = "",
        open var romanian: String = "",
        open var russian: String = "",
        open var schinese: String = "",
        open var spanish: String = "",
        open var swedish: String = "",
        open var tchinese: String = "",
        open var thai: String = "",
        open var turkish: String = "",
        open var ukrainian: String = "",
        open var vietnamese: String = ""
) : RealmObject()

