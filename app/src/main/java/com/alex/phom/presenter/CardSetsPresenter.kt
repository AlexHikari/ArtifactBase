package com.alex.phom.presenter

import android.util.Log
import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardSetView
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.Cardview
import com.alex.phom.models.mappers.toCardSetView

class CardSetsPresenter(private val getCardSetsUseCase: GetCardSetsUseCase, view: CardSetsPresenter.View, errorHandler: ErrorHandler) : Presenter<CardSetsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val cardSets = mutableListOf<CardSetView>()
    private val cardsToShow = mutableListOf<Cardview>()

    override fun initialize() {
        view.showProgress()
        getCardSetsUseCase.execute(
                onNext = {
                    cardSets.add(it.toCardSetView())
                },
                onComplete = {
                    filterCards(cardSets)
                    view.hideProgress()
                    view.showCards(cardsToShow)
                },
                onError = onError { view.showError(it) }
        )
    }

    fun onCardClicked(card: Cardview) {
        Log.i("a", "a")
    }

    private fun filterCards(cardSet: MutableList<CardSetView>) {

        cardSet.forEach {
            it.cardList.forEach { card ->
                if (card.CardType != CardTypeView.PASSIVE_ABILITY && card.CardType != CardTypeView.ABILITY && card.CardType != CardTypeView.UNKNOWN) {
                    this.cardsToShow.add(card)
                }
            }
        }

    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
        getCardSetsUseCase.clear()
    }

    interface View : Presenter.View {
        fun showCards(cardList: List<Cardview>)
    }
}