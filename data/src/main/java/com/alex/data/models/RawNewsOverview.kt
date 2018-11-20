package com.alex.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Actual data class to be stored in Realm
 * @property title String title of the news
 * @property resourceURL String Resource to be fetched (actual news)
 * @property resourceIMG String Image to display in the card
 * @constructor
 */
open class RawNewsOverview(
        val title: String = "",
        @PrimaryKey val resourceURL: String = "",
        val resourceIMG: String = ""
) : RealmObject()

