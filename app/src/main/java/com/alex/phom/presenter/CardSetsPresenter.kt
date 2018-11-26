package com.alex.phom.presenter

import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardSetView
import com.alex.phom.models.mappers.toCardSetView

class CardSetsPresenter(private val getCardSetsUseCase: GetCardSetsUseCase, view: CardSetsPresenter.View, errorHandler: ErrorHandler) : Presenter<CardSetsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val cardSets = mutableListOf<CardSetView>()

    override fun initialize() {
        view.showProgress()
        getCardSetsUseCase.execute(
                onNext = {
                    cardSets.add(it.toCardSetView())
                },
                onComplete = {
                    view.hideProgress()
                    view.showCards(cardSets)
                },
                onError = onError { view.showError(it) }
        )
    }

    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface View : Presenter.View {
        fun showCards(cardSet: List<CardSetView>)
    }
}