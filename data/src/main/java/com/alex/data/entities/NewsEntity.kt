package com.alex.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class NewsCardEntity(
        val title: String = "",
        @PrimaryKey val resourceURL: String = "",
        val resourceIMG: String = ""
) : RealmObject()

