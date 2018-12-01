package com.alex.phom.view.fragment

import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.showMe
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
    private val colorFilter: Array<Boolean> = Array(4) { false }
    // hero-spell-creep-improvement-item
    private val typeFilter: Array<Boolean> = Array(5) { false }
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
            typeFilter[0] = !typeFilter[0]
            if (typeFilter[0])
                heroImg.setBackgroundResource(R.color.colorSelected)
            else
                heroImg.setBackgroundResource(R.color.colorSecondary)
        }

        spellImgButton.setOnClickListener {
            typeFilter[1] = !typeFilter[1]
            if (typeFilter[1])
                spellImg.setBackgroundResource(R.color.colorSelected)
            else
                spellImg.setBackgroundResource(R.color.colorSecondary)
        }

        creepImgButton.setOnClickListener {
            typeFilter[2] = !typeFilter[2]
            if (typeFilter[2])
                creepImg.setBackgroundResource(R.color.colorSelected)
            else
                creepImg.setBackgroundResource(R.color.colorSecondary)
        }

        improvementImgButton.setOnClickListener {
            typeFilter[3] = !typeFilter[3]
            checkColorsAndToggle()
            if (typeFilter[3]) {
                improvementImg.setBackgroundResource(R.color.colorSelected)
            } else
                improvementImg.setBackgroundResource(R.color.colorSecondary)
        }

        itemImgButton.setOnClickListener {
            typeFilter[4] = !typeFilter[4]
            checkColorsAndToggle()
            if (typeFilter[4])
                itemImg.setBackgroundResource(R.color.colorSelected)
            else
                itemImg.setBackgroundResource(R.color.colorSecondary)
        }

        blackColorButton.setOnClickListener {
            colorFilter[0] = !colorFilter[0]
            if (colorFilter[0])
                blackColor.setBackgroundResource(R.color.colorSelected)
            else
                blackColor.setBackgroundResource(R.color.colorSecondary)
        }
        redColorButton.setOnClickListener {
            colorFilter[1] = !colorFilter[1]
            if (colorFilter[1])
                redColor.setBackgroundResource(R.color.colorSelected)
            else
                redColor.setBackgroundResource(R.color.colorSecondary)
        }
        greenColorButton.setOnClickListener {
            colorFilter[2] = !colorFilter[2]
            if (colorFilter[2])
                greenColor.setBackgroundResource(R.color.colorSelected)
            else
                greenColor.setBackgroundResource(R.color.colorSecondary)
        }
        blueColorButton.setOnClickListener {
            colorFilter[3] = !colorFilter[3]
            if (colorFilter[3])
                blueColor.setBackgroundResource(R.color.colorSelected)
            else
                blueColor.setBackgroundResource(R.color.colorSecondary)
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

    private fun checkColorsAndToggle() {

        val disable = typeFilter[3] || typeFilter[4]
        when (disable) {
            true -> {
                colorFilter.map { false }
                disabledColor.visibility = View.VISIBLE

            }
            false -> {
                disabledColor.visibility = View.GONE
            }
        }
    }

    override fun showIcons() {
        activity?.let {
            it.home_header.button_sortby.visibility = View.VISIBLE
            button_filter.visibility = View.VISIBLE
        }
    }

}