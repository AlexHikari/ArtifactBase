package com.alex.phom.presenter.galleryTab

import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.*
import com.alex.phom.models.mappers.toCardSetView
import com.alex.phom.presenter.Presenter

class CardSetsPresenter(private val getCardSetsUseCase: GetCardSetsUseCase, view: View, errorHandler: ErrorHandler) : Presenter<CardSetsPresenter.View>(view = view, errorHandler = errorHandler) {

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
            CardTypeView.HERO -> {
                val referenceList = getReferencedCards(card)
                view.navigateToHeroSingleCard(card, referenceList)
            }
            CardTypeView.ITEM -> {
                view.navigateToItemSingleCard(card)
            }
            else -> {
                val referenceList = getReferencedCards(card)
                view.navigateToGenericSingleCard(card, referenceList)
            }
        }
    }

    private fun getReferencedCards(card: CardView): List<CardView> {
        val references = mutableListOf<CardView>()
        card.references.forEach { reference ->
            cardSets.forEach { cardSet ->
                val card = cardSet.cardList.find {
                    it.cardID == reference.card_id
                }
                card?.let {
                    references.add(it)
                }
            }
        }
        return references
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
        fun navigateToHeroSingleCard(card: CardView, heroReferenceList: List<CardView>)
        fun navigateToGenericSingleCard(card: CardView, heroReferenceList: List<CardView>)
        fun navigateToItemSingleCard(card: CardView)
    }
}