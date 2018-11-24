package com.alex.domain.models

/**
 * Data class to represent the news overview as a Card
 * @property title String
 * @property resourceURL String
 * @property resourceIMG String
 * @constructor
 */
data class NewsOverview(
        val title: String,
        val resourceURL: String,
        val resourceIMG: String
)