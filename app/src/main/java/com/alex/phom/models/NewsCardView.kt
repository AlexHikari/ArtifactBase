package com.alex.phom.models

/**
 * Data class to represent the news overview as a Card
 * @property title String
 * @property resourceURL String
 * @property resourceIMG String
 * @constructor
 */
data class NewsCardView(
        val title: String,
        val resourceURL: String,
        val resourceIMG: String
)