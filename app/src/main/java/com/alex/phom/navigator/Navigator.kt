package com.alex.phom.navigator

import android.content.Context
import android.content.Intent
import com.alex.phom.models.ArticleView
import com.alex.phom.view.activity.ArticleActivity

/**
 * Navigator.
 */

fun navigateToArticle(context: Context, articleViewList: ArrayList<ArticleView>) {
    val intent = Intent(context, ArticleActivity::class.java)
    intent.putParcelableArrayListExtra(ArticleActivity.ARTICLE_BUNDLE, articleViewList)
    context.startActivity(intent)
}