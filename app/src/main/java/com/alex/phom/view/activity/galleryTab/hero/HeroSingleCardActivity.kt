package com.alex.phom.view.activity.galleryTab.hero

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.hero.HeroSingleCardPresenter
import com.alex.phom.view.activity.RootActivity
import com.alex.phom.view.adapter.fragments.HeroCardPageAdapter
import com.alex.phom.view.fragment.galleryTab.hero.HeroDetailsFragment
import com.alex.phom.view.fragment.galleryTab.hero.HeroImageFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_hero.*

class HeroSingleCardActivity : RootActivity<HeroSingleCardPresenter.View>(), HeroSingleCardPresenter.View {


    companion object {
        const val HERO_CARD_BUNDLE = "HERO_CARD_BUNDLE"
        const val HERO_REFERENCE_BUNDLE = "HERO_REFERENCE_BUNDLE"
    }

    private val heroFragmentAdapter: HeroCardPageAdapter = HeroCardPageAdapter(supportFragmentManager)

    override val progress: View by lazy { heroProgressView }
    override val presenter: HeroSingleCardPresenter by instance()
    override val layoutResourceId: Int = R.layout.activity_hero
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HeroSingleCardPresenter>() with provider {
            HeroSingleCardPresenter(
                    view = this@HeroSingleCardActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        heroRecyclerTabLayout.setIndicatorColor(R.color.colorSelected)
        heroRecyclerTabLayout.setBackgroundResource(R.color.colorBlackSource)
        heroRecyclerTabLayout.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    override fun initializeFragmentAdapter(card: CardView, references: List<CardView>) {
        val referenceBundle = mutableListOf<CardView>()
        referenceBundle.add(card)
        referenceBundle.addAll(references)
        heroFragmentAdapter.addFragment(fragment = HeroImageFragment.newInstance(referenceBundle), title = "Overview")
        heroFragmentAdapter.addFragment(fragment = HeroDetailsFragment.newInstance(referenceBundle), title = "Details")
        heroViewPager.adapter = heroFragmentAdapter
        heroRecyclerTabLayout.setUpWithViewPager(heroViewPager)

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