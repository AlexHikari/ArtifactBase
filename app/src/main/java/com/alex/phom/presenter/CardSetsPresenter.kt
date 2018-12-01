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

    fun filterCards(colors: Array<Boolean>, types: Array<Boolean>) {
        filteredCards = mutableListOf()
        filterCardsByColor(colors)
        filterCardsByType(types)
        view.showCards(filteredCards)
    }

    private fun filterCardsByType(types: Array<Boolean>) {
        var shouldReplace = false
        types.forEach {
            if (it && !shouldReplace)
                shouldReplace = true
        }
        if (!shouldReplace) {
            filteredCards.addAll(cardsToShow)
        } else {
            types.forEachIndexed { index, b ->
                when (index) {
                    0 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.CardType == CardTypeView.HERO)
                                filteredCards.add(cardview)
                        }
                    }
                    1 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.CardType == CardTypeView.SPELL)
                                filteredCards.add(cardview)
                        }
                    }
                    2 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.CardType == CardTypeView.CREEP)
                                filteredCards.add(cardview)
                        }
                    }
                    3 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.CardType == CardTypeView.IMPROVEMENT)
                                filteredCards.add(cardview)
                        }
                    }
                    4 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.CardType == CardTypeView.ITEM)
                                filteredCards.add(cardview)
                        }
                    }
                }
            }
        }
    }

    private fun filterCardsByColor(colors: Array<Boolean>) {

        var shouldReplace = false
        colors.forEach {
            if (it && !shouldReplace)
                shouldReplace = true
        }
        if (!shouldReplace) {
            filteredCards.addAll(cardsToShow)
        } else {
            colors.forEachIndexed { index, b ->
                when (index) {
                    0 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.cardColor == CardColorView.BLACK)
                                filteredCards.add(cardview)
                        }
                    }
                    1 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.cardColor == CardColorView.RED)
                                filteredCards.add(cardview)
                        }
                    }
                    2 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.cardColor == CardColorView.GREEN)
                                filteredCards.add(cardview)
                        }
                    }
                    3 -> {
                        if (b) cardsToShow.map { cardview ->
                            if (cardview.cardColor == CardColorView.BLUE)
                                filteredCards.add(cardview)
                        }
                    }
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
        fun showIcons()
    }
}