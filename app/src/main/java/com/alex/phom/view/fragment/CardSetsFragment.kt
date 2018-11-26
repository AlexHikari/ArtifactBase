package com.alex.phom.view.fragment

import android.support.v7.widget.GridLayoutManager
import com.alex.phom.R
import com.alex.phom.models.CardSetView
import com.alex.phom.presenter.CardSetsPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_cardset.*

class CardSetsFragment : RootFragment<CardSetsPresenter.View>(), CardSetsPresenter.View {


    override val presenter: CardSetsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_cardset
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
        }
    }

    override fun registerListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCards(cardSet: List<CardSetView>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}