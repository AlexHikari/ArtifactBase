package com.alex.phom.presenter.galleryTab.generic

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView
import com.alex.phom.presenter.Presenter

class GenericSingleCardPresenter(view: View, errorHandler: ErrorHandler) : Presenter<GenericSingleCardPresenter.View>(view = view, errorHandler = errorHandler) {

    private var heroReference = listOf<CardView>()
    private var genericCard = CardView()
    override fun initialize() {
        view.showProgress()
        genericCard = view.getCard()
        heroReference = view.getHeroReference()
        view.initializeFragmentAdapter(genericCard, heroReference)
        view.hideProgress()
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    interface View : Presenter.View {
        fun getCard(): com.alex.phom.models.CardView
        fun getHeroReference(): List<CardView>
        fun initializeFragmentAdapter(card: CardView, references: List<CardView>)
    }

}