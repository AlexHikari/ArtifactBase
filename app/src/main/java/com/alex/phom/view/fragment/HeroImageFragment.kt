package com.alex.phom.view.fragment

import android.os.Bundle
import com.alex.phom.R
import com.alex.phom.extension.getPremierCard
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView
import com.alex.phom.presenter.HeroImagePresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.hero_image.*

class HeroImageFragment : RootFragment<HeroImagePresenter.View>(), HeroImagePresenter.View {


    companion object {
        private const val HERO_IMAGE_CARD_LIST_BUNDLE = "HERO_IMAGE_CARD_LIST_BUNDLE"

        fun newInstance(cardsToBundle: List<CardView>): HeroImageFragment {
            val heroImageFragment = HeroImageFragment()
            val bundle = Bundle()
            val bundledCards = arrayListOf<CardView>()
            bundledCards.addAll(cardsToBundle)
            bundle.putParcelableArrayList(HERO_IMAGE_CARD_LIST_BUNDLE, bundledCards)
            heroImageFragment.arguments = bundle
            return heroImageFragment
        }
    }

    override val presenter: HeroImagePresenter by instance()
    override val layoutResourceId: Int = R.layout.hero_image
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<HeroImagePresenter>() with provider {
            HeroImagePresenter(
                    view = this@HeroImageFragment,
                    errorHandler = instance()
            )
        }
    }


    override fun initializeUI() {

    }

    override fun registerListeners() {

    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun getReferences(): List<CardView>? = arguments?.getParcelableArrayList(HERO_IMAGE_CARD_LIST_BUNDLE)


    override fun showCards(heroBundleCards: List<CardView>) {
        val heroCard: CardView? = heroBundleCards.find { it.CardType == CardTypeView.HERO }
        if (heroCard != null) {
            heroCardDisplayImage.loadWithPlaceholder(heroCard.largeImage.imgDef, R.drawable.cardplaceholder)
            heroPremierCardDisplayImage.loadWithPlaceholder(heroBundleCards.getPremierCard(), R.drawable.cardplaceholder)
        } else {
            //do something but will never be null
        }
    }
}