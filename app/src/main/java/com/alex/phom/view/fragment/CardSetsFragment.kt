package com.alex.phom.view.fragment

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
    private val colorfilter: Array<Boolean> = Array(4) { false }
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
        blackColorButton.setOnClickListener {
            colorfilter[0] = !colorfilter[0]
            if (colorfilter[0])
                blackColor.setBackgroundResource(R.color.colorSelected)
            else
                blackColor.setBackgroundResource(R.color.colorSecondary)
        }
        redColorButton.setOnClickListener {
            colorfilter[1] = !colorfilter[1]
            if (colorfilter[1])
                redColor.setBackgroundResource(R.color.colorSelected)
            else
                redColor.setBackgroundResource(R.color.colorSecondary)
        }
        greenColorButton.setOnClickListener {
            colorfilter[2] = !colorfilter[2]
            if (colorfilter[2])
                greenColor.setBackgroundResource(R.color.colorSelected)
            else
                greenColor.setBackgroundResource(R.color.colorSecondary)
        }
        blueColorButton.setOnClickListener {
            colorfilter[3] = !colorfilter[3]
            if (colorfilter[3])
                blueColor.setBackgroundResource(R.color.colorSelected)
            else
                blueColor.setBackgroundResource(R.color.colorSecondary)
        }

        button_filter.setOnClickListener {

            when (filterView.visibility) {
                View.VISIBLE -> {
                    filterView.visibility = View.GONE
                    presenter.filterCardsByColor(colorfilter)
                }
                View.GONE -> {
                    filterView.visibility = View.VISIBLE
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