package com.alex.phom.navigator

import android.content.Context
import android.content.Intent
import com.alex.phom.view.activity.ArticleActivity

/**
 * Navigator.
 */

fun navigateToArticle(context: Context, url: String) {
    val intent = Intent(context, ArticleActivity::class.java)
    intent.putExtra(ArticleActivity.ARTICLE_URL, url)
    context.startActivity(intent)
}