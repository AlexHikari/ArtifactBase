package com.alex.phom.view.fragment.galleryTab.hero

import android.os.Bundle
import android.text.Html
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.load
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.hero.HeroDetailsPresenter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_hero_details.*

class HeroDetailsFragment : RootFragment<HeroDetailsPresenter.View>(), HeroDetailsPresenter.View {


    companion object {
        private const val HERO_DETAILS_CARD_LIST_BUNDLE = "HERO_DETAILS_CARD_LIST_BUNDLE"

        fun newInstance(references: List<CardView>): HeroDetailsFragment {
            val heroDetailsFragment = HeroDetailsFragment()
            val bundle = Bundle()
            val referenceBundle = ArrayList<CardView>()
            references.forEach { reference ->
                referenceBundle.add(reference)
            }
            bundle.putParcelableArrayList(HERO_DETAILS_CARD_LIST_BUNDLE, referenceBundle)
            heroDetailsFragment.arguments = bundle
            return heroDetailsFragment
        }
    }

    override val presenter: HeroDetailsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_hero_details
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<HeroDetailsPresenter>() with provider {
            HeroDetailsPresenter(
                    view = this@HeroDetailsFragment,
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

    override fun getReferences(): List<CardView>? = arguments?.getParcelableArrayList(HERO_DETAILS_CARD_LIST_BUNDLE)

    override fun showCardDetails(heroBundleCards: List<CardView>) {
        val heroCard: CardView? = heroBundleCards.find { it.CardType == CardTypeView.HERO }
        if (heroCard != null) {
            heroIconImage.load(heroCard.heroInGameImage.img)
            heroName.text = heroCard.cardName.english
            if (heroCard.attack == 0L) heroAttackText.text = "_" else heroAttackText.text = heroCard.attack.toString()
            if (heroCard.armor == 0L) heroArmorText.text = "_" else heroArmorText.text = heroCard.armor.toString()
            if (heroCard.hitPoints == 0L) heroHitPointsText.text = "_" else heroHitPointsText.text = heroCard.hitPoints.toString()
            heroActiveAbilityText.text = Html.fromHtml(heroCard.cardText.english, Html.FROM_HTML_MODE_COMPACT)
        } else {
            //do something but will never be null
        }
    }
}
