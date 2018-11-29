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
        cards_recycler_view.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = cardAdapter
        }
    }

    override fun registerListeners() {
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun showCards(cardList: List<Cardview>) {
        cardAdapter.addAll(cardList.toMutableList())
    }

    override fun showIcons() {
        activity?.let {
            it.home_header.button_filter.visibility = View.VISIBLE
            it.home_header.button_sortby.visibility = View.VISIBLE
        }
    }

}