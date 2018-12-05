package com.alex.phom.view.activity

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.CardView
import com.alex.phom.presenter.HeroSingleCardPresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.card_hero.*

class HeroSingleCardActivity : RootActivity<HeroSingleCardPresenter.View>(), HeroSingleCardPresenter.View {


    companion object {
        const val HERO_CARD_BUNDLE = "HERO_CARD_BUNDLE"
    }

    override val progress: View by lazy { progressView }

    override val presenter: HeroSingleCardPresenter by instance()
    override val layoutResourceId: Int = R.layout.card_hero
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HeroSingleCardPresenter>() with provider {
            HeroSingleCardPresenter(
                    view = this@HeroSingleCardActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {

    }

    override fun registerListeners() {
        heroCardDisplayLarge.setOnClickListener {
            heroCardDisplayLarge.visibility = View.GONE
            heroProperties.visibility = View.VISIBLE
        }
        heroProperties.setOnClickListener {
            heroProperties.visibility = View.GONE
            heroCardDisplayLarge.visibility = View.VISIBLE
        }

    }

    override fun showCard(card: CardView) {
        heroCardDisplayImage.load(card.largeImage.imgDef)
        heroName.text = card.cardName.english
        heroAttackText.text = card.attack.toString()
        heroArmor.text = card.armor.toString()
        heroHitPointsText.text = card.hitPoints.toString()
    }

    override fun getCard(): CardView {
        return intent.getParcelableExtra(HERO_CARD_BUNDLE)
    }
}