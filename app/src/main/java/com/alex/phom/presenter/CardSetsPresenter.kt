package com.alex.phom.presenter

import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.*
import com.alex.phom.models.mappers.toCardSetView

class CardSetsPresenter(private val getCardSetsUseCase: GetCardSetsUseCase, view: CardSetsPresenter.View, errorHandler: ErrorHandler) : Presenter<CardSetsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val cardSets = mutableListOf<CardSetView>()
    private val cardsToShow = mutableListOf<CardView>()
    private var filteredCards = mutableListOf<CardView>()

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

    fun onCardClicked(card: CardView) {
        when (card.CardType) {
            CardTypeView.HERO -> view.navigateToHeroSingleCard(card)
        }
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

    fun filterCards(colors: ArrayList<CardColorView>, types: ArrayList<CardTypeView>, rarities: ArrayList<RarityView>, minMana: Int, maxMana: Int, minGold: Int, maxGold: Int) {

        filteredCards = cardsToShow.filter {
            if (colors.isNotEmpty()) {
                if (!colors.contains(it.cardColor)) {
                    return@filter false
                }
            }
            if (types.isNotEmpty()) {
                if (!types.contains(it.CardType)) {
                    return@filter false
                }
            }
            if (rarities.isNotEmpty()) {
                if (!rarities.contains(it.rarity)) {
                    return@filter false
                }
            }

            if (types.contains(CardTypeView.ITEM) && it.goldCost.toInt() != 0) {
                if (it.goldCost !in minGold..maxGold) {
                    return@filter false
                }
            } else {
                if (!types.contains(CardTypeView.HERO) && it.manaCost.toInt() != 0) {
                    if (it.manaCost !in minMana..maxMana) {
                        return@filter false
                    }
                }
            }
            return@filter true
        }.toMutableList()
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
        fun showCards(cardList: List<CardView>)
        fun showIcons()
        fun navigateToHeroSingleCard(card: CardView)
    }
}