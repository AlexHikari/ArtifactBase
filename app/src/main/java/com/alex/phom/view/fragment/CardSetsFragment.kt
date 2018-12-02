package com.alex.phom.view.fragment

import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardColorView
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.Cardview
import com.alex.phom.presenter.CardSetsPresenter
import com.alex.phom.view.adapter.CardAdapter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_cardset.*
import kotlinx.android.synthetic.main.header_bar.view.*

class CardSetsFragment : RootFragment<CardSetsPresenter.View>(), CardSetsPresenter.View {


    companion object {
        fun newInstance(): CardSetsFragment = CardSetsFragment()
    }

    // black-red-green-blue
    private val colorFilter = arrayListOf<CardColorView>()
    // hero-spell-creep-improvement-item
    private val typeFilter = arrayListOf<CardTypeView>()
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


    override fun registerListeners() {
        mana_Rangeseekbar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            mana_leftvalue.text = minValue.toString()
            mana_rightvalue.text = maxValue.toString()
        }

        heroImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.HERO)) {
                heroImg.setBackgroundResource(R.color.colorSecondary)
                typeFilter.removeIf { type -> type == CardTypeView.HERO }
            } else {
                typeFilter.add(CardTypeView.HERO)
                heroImg.setBackgroundResource(R.color.colorSelected)
            }
        }

        spellImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.SPELL)) {
                spellImg.setBackgroundResource(R.color.colorSecondary)
                typeFilter.removeIf { type -> type == CardTypeView.SPELL }
            } else {
                typeFilter.add(CardTypeView.SPELL)
                spellImg.setBackgroundResource(R.color.colorSelected)
            }
        }

        creepImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.CREEP)) {
                creepImg.setBackgroundResource(R.color.colorSecondary)
                typeFilter.removeIf { type -> type == CardTypeView.CREEP }
            } else {
                typeFilter.add(CardTypeView.CREEP)
                creepImg.setBackgroundResource(R.color.colorSelected)
            }
        }

        improvementImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.IMPROVEMENT)) {
                improvementImg.setBackgroundResource(R.color.colorSecondary)
                typeFilter.removeIf { type -> type == CardTypeView.IMPROVEMENT }
            } else {
                typeFilter.add(CardTypeView.IMPROVEMENT)
                improvementImg.setBackgroundResource(R.color.colorSelected)
            }
        }


        itemImgButton.setOnClickListener {
            if (typeFilter.contains(CardTypeView.ITEM)) {
                itemImg.setBackgroundResource(R.color.colorSecondary)
                disabledColor.visibility = View.GONE
                typeFilter.removeIf { type -> type == CardTypeView.ITEM }
            } else {
                typeFilter.add(CardTypeView.ITEM)
                blueColor.setBackgroundResource(R.color.colorSecondary)
                blackColor.setBackgroundResource(R.color.colorSecondary)
                greenColor.setBackgroundResource(R.color.colorSecondary)
                redColor.setBackgroundResource(R.color.colorSecondary)
                disabledColor.visibility = View.VISIBLE
                colorFilter.clear()
                itemImg.setBackgroundResource(R.color.colorSelected)
            }
        }

        blackColorButton.setOnClickListener {
            if (colorFilter.contains(CardColorView.BLACK)) {
                blackColor.setBackgroundResource(R.color.colorSecondary)
                colorFilter.removeIf { color -> color == CardColorView.BLACK }
            } else {
                colorFilter.add(CardColorView.BLACK)
                blackColor.setBackgroundResource(R.color.colorSelected)
            }
        }
        blueColorButton.setOnClickListener {
            if (colorFilter.contains(CardColorView.BLUE)) {
                blueColor.setBackgroundResource(R.color.colorSecondary)
                colorFilter.removeIf { color -> color == CardColorView.BLUE }
            } else {
                colorFilter.add(CardColorView.BLUE)
                blueColor.setBackgroundResource(R.color.colorSelected)
            }
        }
        redColorButton.setOnClickListener {
            if (colorFilter.contains(CardColorView.RED)) {
                redColor.setBackgroundResource(R.color.colorSecondary)
                colorFilter.removeIf { color -> color == CardColorView.RED }
            } else {
                colorFilter.add(CardColorView.RED)
                redColor.setBackgroundResource(R.color.colorSelected)
            }
        }
        greenColorButton.setOnClickListener {
            if (colorFilter.contains(CardColorView.GREEN)) {
                greenColor.setBackgroundResource(R.color.colorSecondary)
                colorFilter.removeIf { color -> color == CardColorView.GREEN }
            } else {
                colorFilter.add(CardColorView.GREEN)
                greenColor.setBackgroundResource(R.color.colorSelected)
            }
        }

        button_filter.setOnClickListener {
            when (filterView.visibility) {
                View.VISIBLE -> {
                    filterView.visibility = View.GONE
                    presenter.filterCards(colorFilter, typeFilter)
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

    override fun showCards(cardList: List<Cardview>) {
        cardAdapter.replace(cardList.toMutableList())
    }


    override fun showIcons() {
        activity?.let {
            it.home_header.button_sortby.visibility = View.VISIBLE
            button_filter.visibility = View.VISIBLE
        }
    }

}