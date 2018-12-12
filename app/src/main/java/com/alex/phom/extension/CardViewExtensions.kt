package com.alex.phom.extension

import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView

fun List<CardView>.getPremierCardImage(): String {
    val premierCard = this.find {
        !(it.CardType == CardTypeView.ABILITY || it.CardType == CardTypeView.PASSIVE_ABILITY || it.CardType == CardTypeView.HERO)
    }
    premierCard?.let {
        return it.largeImage.imgDef
    }
}


fun List<CardView>.getPremierCardText(): String {
    val premierCard = this.find {
        !(it.CardType == CardTypeView.ABILITY || it.CardType == CardTypeView.PASSIVE_ABILITY || it.CardType == CardTypeView.HERO)
    }
    premierCard?.let {
        return it.cardText.english
    }
}

fun List<CardView>.getHeroCard(): CardView? = this.find {
    it.CardType == CardTypeView.HERO
}


fun List<CardView>.getGenericCard(): CardView? = this.find { it.CardType == CardTypeView.SPELL || it.CardType == CardTypeView.CREEP || it.CardType == CardTypeView.IMPROVEMENT }