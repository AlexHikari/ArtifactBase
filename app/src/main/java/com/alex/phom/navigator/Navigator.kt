package com.alex.phom.navigator

import android.content.Context
import android.content.Intent
import com.alex.phom.models.ArticleView
import com.alex.phom.models.CardView
import com.alex.phom.view.activity.galleryTab.generic.GenericSingleCardActivity
import com.alex.phom.view.activity.galleryTab.hero.HeroSingleCardActivity
import com.alex.phom.view.activity.galleryTab.item.ItemSingleCardActivity
import com.alex.phom.view.activity.newsTab.ArticleActivity

/**
 * Navigator.
 */

fun navigateToArticle(context: Context, articleViewList: ArrayList<ArticleView>) {
    val intent = Intent(context, ArticleActivity::class.java)
    intent.putParcelableArrayListExtra(ArticleActivity.ARTICLE_BUNDLE, articleViewList)
    context.startActivity(intent)
}

fun navigateToHeroSingleCard(context: Context, card: CardView, heroReferenceList: List<CardView>) {
    val intent = Intent(context, HeroSingleCardActivity::class.java)
    intent.putExtra(HeroSingleCardActivity.HERO_CARD_BUNDLE, card)
    val references = ArrayList<CardView>()
    heroReferenceList.forEach {
        references.add(it)
    }
    intent.putParcelableArrayListExtra(HeroSingleCardActivity.HERO_REFERENCE_BUNDLE, references)
    context.startActivity(intent)
}

fun navigateToItemSingleCard(context: Context, card: CardView) {

    val intent = Intent(context, ItemSingleCardActivity::class.java)
    intent.putExtra(ItemSingleCardActivity.ITEM_CARD_BUNDLE, card)
    context.startActivity(intent)
}

fun navigateToGenericSingleCard(context: Context, card: CardView, heroReferenceList: List<CardView>) {
    val intent = Intent(context, GenericSingleCardActivity::class.java)
    intent.putExtra(GenericSingleCardActivity.GENERIC_CARD, card)
    val references = ArrayList<CardView>()
    heroReferenceList.forEach {
        references.add(it)
    }
    intent.putParcelableArrayListExtra(GenericSingleCardActivity.GENERIC_HERO_REFERENCE, references)
    context.startActivity(intent)
}