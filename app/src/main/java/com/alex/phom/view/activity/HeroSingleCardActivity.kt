package com.alex.phom.view.activity

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.CardView
import com.alex.phom.presenter.HeroSingleCardPresenter
import com.alex.phom.view.adapter.HeroCardPageAdapter
import com.alex.phom.view.fragment.HeroDetailsFragment
import com.alex.phom.view.fragment.HeroImageFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.card_hero.*

class HeroSingleCardActivity : RootActivity<HeroSingleCardPresenter.View>(), HeroSingleCardPresenter.View {


    companion object {
        const val HERO_CARD_BUNDLE = "HERO_CARD_BUNDLE"
        const val HERO_REFERENCE_BUNDLE = "HERO_REFERENCE_BUNDLE"
    }

    private val heroFragmentAdapter: HeroCardPageAdapter = HeroCardPageAdapter(supportFragmentManager)

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
        recyclerTabLayout.setIndicatorColor(R.color.colorSelected)
        recyclerTabLayout.setBackgroundResource(R.color.colorBlackSource)
        recyclerTabLayout.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    override fun initializeFragmentAdapter(card: CardView, references: List<CardView>) {
        val referenceBundle = mutableListOf<CardView>()
        referenceBundle.add(card)
        referenceBundle.addAll(references)
        heroFragmentAdapter.addFragment(fragment = HeroImageFragment.newInstance(referenceBundle), title = "Overview")
        heroFragmentAdapter.addFragment(fragment = HeroDetailsFragment.newInstance(referenceBundle), title = "Details")
        heroViewPager.adapter = heroFragmentAdapter
        recyclerTabLayout.setUpWithViewPager(heroViewPager)

    }

    override fun registerListeners() {

    }

    override fun getCard(): CardView {
        return intent.getParcelableExtra(HERO_CARD_BUNDLE)
    }

    override fun getReferences(): List<CardView> {
        val auxList: ArrayList<CardView> = intent.getParcelableArrayListExtra(HERO_REFERENCE_BUNDLE)
        return auxList.toList()
    }
}