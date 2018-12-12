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

/**
 * Navigates to article and bundles an array of articles
 * @param context Context
 * @param articleViewList ArrayList<ArticleView>
 */
fun navigateToArticle(context: Context, articleViewList: ArrayList<ArticleView>) {
    val intent = Intent(context, ArticleActivity::class.java)
    intent.putParcelableArrayListExtra(ArticleActivity.ARTICLE_BUNDLE, articleViewList)
    context.startActivity(intent)
}

/**
 * Navigates to a single hero card view and bundles the hero card and the associated cards with it
 * @param context Context
 * @param card CardView
 * @param heroReferenceList List<CardView>
 */
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

/**
 * Navigates to single item card and bundles the item card
 * @param context Context
 * @param card CardView
 */
fun navigateToItemSingleCard(context: Context, card: CardView) {

    val intent = Intent(context, ItemSingleCardActivity::class.java)
    intent.putExtra(ItemSingleCardActivity.ITEM_CARD_BUNDLE, card)
    context.startActivity(intent)
}

/**
 * navigates to single generic card and bundles the card and the associated cards with it
 * @param context Context
 * @param card CardView
 * @param heroReferenceList List<CardView> Can be empty
 */
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