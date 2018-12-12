package com.alex.phom.extension

import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView

/**
 * Gets the image of the premier card associated with the hero. It doesn't search for it, must be in the same list with no other heroes
 * @receiver List<CardView> Computed list of hero , [premier cards]
 * @return String returns the image of the associated premier card
 */
fun List<CardView>.getPremierCardImage(): String {
    val premierCard = this.find {
        !(it.CardType == CardTypeView.ABILITY || it.CardType == CardTypeView.PASSIVE_ABILITY || it.CardType == CardTypeView.HERO)
    }
    premierCard?.let {
        return it.largeImage.imgDef
    }
}

/**
 * Gets the text of the premier card associated with the hero. It doesn't search for it, must be in the same list with no other heroes
 * @receiver List<CardView> Computed list of hero , [premier cards]
 * @return String returns the text of the associated premier card
 */
fun List<CardView>.getPremierCardText(): String {
    val premierCard = this.find {
        !(it.CardType == CardTypeView.ABILITY || it.CardType == CardTypeView.PASSIVE_ABILITY || it.CardType == CardTypeView.HERO)
    }
    premierCard?.let {
        return it.cardText.english
    }
}

/**
 * Gets the first hero card inside a precomputed List of cards
 * @receiver List<CardView> Computed list of hero , [premier cards]
 * @return String returns the image of the associated premier card
 */
fun List<CardView>.getFirstHeroCard(): CardView? = this.find {
    it.CardType == CardTypeView.HERO
}

/**
 * Get first generic card inside a precomputed List of cards
 * @receiver List<CardView>
 * @return CardView?
 */
fun List<CardView>.getFirstGenericCard(): CardView? = this.find { it.CardType == CardTypeView.SPELL || it.CardType == CardTypeView.CREEP || it.CardType == CardTypeView.IMPROVEMENT }