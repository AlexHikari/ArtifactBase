package com.alex.domain.models

data class Article(
        val post_url: String,
        val post_title: String,
        val post_date: String,
        val post_image: String,
        val post_text: String,
        val selected: Boolean
)
