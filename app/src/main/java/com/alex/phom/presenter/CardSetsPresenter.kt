package com.alex.phom.presenter

import android.util.Log
import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardColorView
import com.alex.phom.models.CardSetView
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.Cardview
import com.alex.phom.models.mappers.toCardSetView

class CardSetsPresenter(private val getCardSetsUseCase: GetCardSetsUseCase, view: CardSetsPresenter.View, errorHandler: ErrorHandler) : Presenter<CardSetsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val cardSets = mutableListOf<CardSetView>()
    private val cardsToShow = mutableListOf<Cardview>()
    private var filteredCards = mutableListOf<Cardview>()

    override fun initialize() {
        view.showProgress()
        getCardSetsUseCase.execute(
                onNext = {
                    cardSets.add(it.toCardSetView())
                },
                onComplete = {
                    firstFilterCards(cardSets)
                    view.showCards(cardsToShow)
                    view.showIcons()
                    view.hideProgress()
                },
                onError = onError { view.showError(it) }
        )
    }

    fun onCardClicked(card: Cardview) {
        Log.i("a", "a")
    }

    private fun firstFilterCards(cardSet: MutableList<CardSetView>) {

        cardSet.forEach {
            it.cardList.forEach { card ->
                if (card.CardType != CardTypeView.PASSIVE_ABILITY && card.CardType != CardTypeView.ABILITY && card.CardType != CardTypeView.UNKNOWN) {
                    this.cardsToShow.add(card)
                }
            }
        }

    }

    fun filterCards(colors: ArrayList<CardColorView>, types: ArrayList<CardTypeView>) {
        filteredCards = mutableListOf()
        if (colors.isEmpty() && types.isEmpty()) {
            filteredCards.addAll(cardsToShow)
        } else {
            if (colors.isNotEmpty()) {
                colors.map { color ->
                    if (types.isNotEmpty()) {
                        types.map { type ->
                            cardsToShow.forEach { card ->
                                if (card.cardColor == color && card.CardType == type) {
                                    filteredCards.add(card)
                                }
                            }
                        }
                    } else {
                        cardsToShow.forEach { card ->
                            if (card.cardColor == color) {
                                filteredCards.add(card)
                            }
                        }
                    }
                }
            } else {
                types.map { type ->
                    cardsToShow.forEach { card ->
                        if (card.CardType == type) {
                            filteredCards.add(card)
                        }
                    }
                }
            }
        }
        view.showCards(filteredCards)
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
        fun showIcons()
    }
}