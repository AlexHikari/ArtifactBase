package com.alex.data.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CardVo(

        open var armor: Long = 0,
        open var attack: Long = 0,
        open var base_card_id: Long = 0,
        @PrimaryKey
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

@RealmClass
open class ReferenceVo(
        open var card_id: Long = 0,
        open var ref_type: String = "",
        open var count: Long = 0

) : RealmObject()

@RealmClass
open class CardSetVo(
        open var card_list: RealmList<CardVo> = RealmList(),
        open var set_info: SetInfoVo = SetInfoVo(),
        open var version: Long = 0,
        @PrimaryKey
        open var set_id: Long = 0
) : RealmObject()

@RealmClass
open class SetInfoVo(

        open var name: NameVo = NameVo(),
        open var pack_item_def: Long = 0,
        open var set_id: Long = 0
) : RealmObject()

@RealmClass
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

@RealmClass
open class MiniImageVo(
        open var img: String = ""
) : RealmObject()

@RealmClass
open class LargeImageVo(

        open var brazilian: String = "",
        open var imgdef: String = "",
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

@RealmClass
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

@RealmClass
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

