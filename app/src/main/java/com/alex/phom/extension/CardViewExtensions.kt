package com.alex.phom.extension

import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView

fun List<CardView>.getPremierCard(): String {
    val premierCard = this.find {
        !(it.CardType == CardTypeView.ABILITY || it.CardType == CardTypeView.PASSIVE_ABILITY)
    }
    premierCard?.let {
        return it.largeImage.imgDef
    }
}