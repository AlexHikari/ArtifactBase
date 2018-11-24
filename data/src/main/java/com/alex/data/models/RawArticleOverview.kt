package com.alex.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RawArticleOverview(
        @PrimaryKey var post_url: String = "",
        var post_title: String = "",
        var post_date: String = "",
        var post_image: String = "",
        var post_text: String = "",
        var selected: Boolean = false
) : RealmObject()