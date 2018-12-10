package com.alex.phom.presenter

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView

class HeroSingleCardPresenter(view: HeroSingleCardPresenter.View, errorHandler: ErrorHandler) : Presenter<HeroSingleCardPresenter.View>(view = view, errorHandler = errorHandler) {

    private var references = listOf<CardView>()
    private var heroCard = CardView()
    override fun initialize() {
        view.showProgress()
        heroCard = view.getCard()
        references = view.getReferences()
        view.hideProgress()
        view.showCard(heroCard, references)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    interface View : Presenter.View {
        fun showCard(card: CardView, references: List<CardView>)
        fun getCard(): com.alex.phom.models.CardView
        fun getReferences(): List<CardView>
    }

}