package com.alex.phom.navigator

import android.content.Context
import android.content.Intent
import com.alex.phom.models.Article
import com.alex.phom.view.activity.ArticleActivity

/**
 * Navigator.
 */

fun navigateToArticle(context: Context, articleList: ArrayList<Article>) {
    val intent = Intent(context, ArticleActivity::class.java)
    intent.putParcelableArrayListExtra(ArticleActivity.ARTICLE_BUNDLE, articleList)
    context.startActivity(intent)
}