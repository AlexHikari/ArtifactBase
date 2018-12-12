package com.alex.phom.view.fragment.galleryTab

import android.support.constraint.ConstraintLayout
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Button
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardColorView
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView
import com.alex.phom.models.RarityView
import com.alex.phom.presenter.galleryTab.CardSetsPresenter
import com.alex.phom.view.adapter.recyclerViews.CardAdapter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_cardset.*
import kotlinx.android.synthetic.main.view_filter.view.*

class CardSetsFragment : RootFragment<CardSetsPresenter.View>(), CardSetsPresenter.View {

    companion object {
        fun newInstance(): CardSetsFragment = CardSetsFragment()
        private const val MIN_MANA = 1
        private const val MAX_MANA = 10
        private const val MIN_GOLD = 1
        private const val MAX_GOLD = 25
    }

    // ******* Those apply to all Cards and filter them in the presenterHero ******* //
    private val colorFilter = arrayListOf<CardColorView>()
    private val typeFilter = arrayListOf<CardTypeView>()
    private val rarityFilter = arrayListOf<RarityView>()
    private var minMana: Int = MIN_MANA
    private var maxMana: Int = MAX_MANA
    private var minGold: Int = MIN_GOLD
    private var maxGold: Int = MAX_GOLD


    override val presenter: CardSetsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_cardset
    private val cardAdapter: CardAdapter = CardAdapter {
        presenter.onCardClicked(it)
    }
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<CardSetsPresenter>() with provider {
            CardSetsPresenter(
                    view = this@CardSetsFragment,
                    errorHandler = instance(),
                    getCardSetsUseCase = instance()
            )
        }
    }


    override fun initializeUI() {
        colorFilter.clear()
        typeFilter.clear()
        cardsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = cardAdapter
        }
    }

    /**
     *  This function takes the rarity element toggles it and sets the rarity filter
     * @param button Button Rarity button in the filter
     * @param rarity RarityView The Rarity of the element to toggle
     * @param backgroundColorDefault Int
     * @param backgroundColorSelected Int
     * @param textColorSelected Int
     * @param textColorDefault Int
     */
    private fun setRarityAndToggle(button: Button, rarity: RarityView, backgroundColorDefault: Int, backgroundColorSelected: Int, textColorSelected: Int, textColorDefault: Int) {
        if (rarityFilter.contains(rarity)) {
            button.setBackgroundResource(backgroundColorDefault)
            button.setTextColor(resources.getColor(textColorDefault, null))
            rarityFilter.removeIf { rar -> rar == rarity }
        } else {
            button.setBackgroundResource(backgroundColorSelected)
            button.setTextColor(resources.getColor(textColorSelected, null))
            rarityFilter.add(rarity)
        }
    }

    /**
     * This function takes the type element toggles it and sets the type filter
     * @param layout ConstraintLayout Layout of the type element
     * @param type CardTypeView type of the element to toggle
     * @param backgroundColorDefault Int
     * @param backgroundColorSelected Int
     */
    private fun setTypeAndToggle(layout: ConstraintLayout, type: CardTypeView, backgroundColorDefault: Int, backgroundColorSelected: Int) {
        if (typeFilter.contains(type)) {
            layout.setBackgroundResource(backgroundColorDefault)
            typeFilter.removeIf { ty -> ty == type }
        } else {
            typeFilter.add(type)
            layout.setBackgroundResource(backgroundColorSelected)
        }
    }

    /**
     * This function takes the color element toggles it and sets the color filter
     * @param layout ConstraintLayout Layout of the color element
     * @param color CardColorView color of the element to toggle
     * @param backgroundColorDefault Int
     * @param backgroundColorSelected Int
     */
    private fun setColorAndToggle(layout: ConstraintLayout, color: CardColorView, backgroundColorDefault: Int, backgroundColorSelected: Int) {
        if (colorFilter.contains(color)) {
            layout.setBackgroundResource(backgroundColorDefault)
            colorFilter.removeIf { co -> co == color }
        } else {
            colorFilter.add(color)
            layout.setBackgroundResource(backgroundColorSelected)
        }
    }

    override fun registerListeners() {
        filterView.goldRangeSeekBar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            minGold = minValue.toInt()
            maxGold = maxValue.toInt()
            filterView.goldLeftValue.text = minValue.toString()
            filterView.goldRightValue.text = maxValue.toString()
        }

        filterView.manaRangeSeekBar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            minMana = minValue.toInt()
            maxMana = maxValue.toInt()
            filterView.manaLeftValue.text = minValue.toString()
            filterView.manaRightValue.text = maxValue.toString()
        }

        filterView.basicRarityButton.setOnClickListener {
            setRarityAndToggle(
                    button = filterView.basicRarityButton,
                    rarity = RarityView.UNKNOWN,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    textColorDefault = R.color.colorTextColor,
                    textColorSelected = R.color.colorTextColorDark)
        }
        filterView.commonRarityButton.setOnClickListener {
            setRarityAndToggle(
                    button = filterView.commonRarityButton,
                    rarity = RarityView.COMMON,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    textColorDefault = R.color.colorTextColor,
                    textColorSelected = R.color.colorTextColorDark)
        }
        filterView.uncommonCard.setOnClickListener {
            setRarityAndToggle(
                    button = filterView.uncommonCard,
                    rarity = RarityView.UNCOMMON,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    textColorDefault = R.color.colorTextColor,
                    textColorSelected = R.color.colorTextColorDark)
        }
        filterView.rareRarityButton.setOnClickListener {
            setRarityAndToggle(
                    button = filterView.rareRarityButton,
                    rarity = RarityView.RARE,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    textColorDefault = R.color.colorTextColor,
                    textColorSelected = R.color.colorTextColorDark)
        }
        filterView.heroImgButton.setOnClickListener {
            setTypeAndToggle(
                    layout = filterView.heroTypeLayout,
                    backgroundColorSelected = R.color.colorSelected,
                    backgroundColorDefault = R.color.colorSecondary,
                    type = CardTypeView.HERO)
        }
        filterView.spellImgButton.setOnClickListener {
            setTypeAndToggle(
                    layout = filterView.spellTypeLayout,
                    backgroundColorSelected = R.color.colorSelected,
                    backgroundColorDefault = R.color.colorSecondary,
                    type = CardTypeView.SPELL)
        }
        filterView.creepImgButton.setOnClickListener {
            setTypeAndToggle(
                    layout = filterView.creepTypeLayout,
                    backgroundColorSelected = R.color.colorSelected,
                    backgroundColorDefault = R.color.colorSecondary,
                    type = CardTypeView.CREEP)
        }
        filterView.improvementImgButton.setOnClickListener {
            setTypeAndToggle(
                    layout = filterView.improvementTypeLayout,
                    backgroundColorSelected = R.color.colorSelected,
                    backgroundColorDefault = R.color.colorSecondary,
                    type = CardTypeView.IMPROVEMENT)
        }

        //Special type item we cannot set this in the function
        filterView.itemImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.ITEM)) {
                //Toggle visibilities of the elements

                filterView.goldTittle.visibility = View.GONE
                filterView.goldLayout.visibility = View.GONE
                filterView.itemTypeLayout.setBackgroundResource(R.color.colorSecondary)
                typeFilter.removeIf { type -> type == CardTypeView.ITEM }

            } else {
                typeFilter.add(CardTypeView.ITEM)
                filterView.itemTypeLayout.setBackgroundResource(R.color.colorSelected)
                //Toggle visibilities of the elements
                filterView.goldTittle.visibility = View.VISIBLE
                filterView.goldLayout.visibility = View.VISIBLE
            }
        }

        filterView.blackColorButton.setOnClickListener {
            setColorAndToggle(
                    layout = filterView.blackColor,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    color = CardColorView.BLACK)
        }
        filterView.blueColorButton.setOnClickListener {
            setColorAndToggle(
                    layout = filterView.blueColor,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    color = CardColorView.BLUE)
        }
        filterView.redColorButton.setOnClickListener {
            setColorAndToggle(
                    layout = filterView.redColor,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    color = CardColorView.RED)
        }
        filterView.greenColorButton.setOnClickListener {
            setColorAndToggle(
                    layout = filterView.greenColor,
                    backgroundColorDefault = R.color.colorSecondary,
                    backgroundColorSelected = R.color.colorSelected,
                    color = CardColorView.GREEN)
        }

        button_filter.setOnClickListener {
            when (filterView.visibility) {
                View.VISIBLE -> {
                    filterView.visibility = View.GONE
                    presenter.filterCards(
                            colors = colorFilter,
                            types = typeFilter,
                            rarities = rarityFilter,
                            minMana = minMana,
                            maxMana = maxMana,
                            minGold = minGold,
                            maxGold = maxGold)
                    context?.let { context ->
                        button_filter.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.colorBottomNavigationNotification)
                    }
                }
                View.GONE -> {
                    filterView.visibility = View.VISIBLE
                    context?.let { context ->
                        button_filter.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.colorSelected)
                    }
                }
                else -> View.VISIBLE
            }

        }
    }


    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun showCards(cardList: List<CardView>) {
        cardAdapter.replace(cardList.toMutableList())
    }


    override fun showIcons() {
        activity?.let {
            button_filter.visibility = View.VISIBLE
        }
    }

    override fun navigateToHeroSingleCard(card: CardView, heroReferenceList: List<CardView>) {
        context?.let {
            com.alex.phom.navigator.navigateToHeroSingleCard(context = it, card = card, heroReferenceList = heroReferenceList)
        }
    }

    override fun navigateToItemSingleCard(card: CardView) {
        context?.let {
            com.alex.phom.navigator.navigateToItemSingleCard(context = it, card = card)
        }
    }

}