package com.alex.phom.view.activity.galleryTab.generic

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.generic.GenericSingleCardPresenter
import com.alex.phom.view.activity.RootActivity
import com.alex.phom.view.adapter.fragments.GenericCardPageAdapter
import com.alex.phom.view.fragment.galleryTab.generic.GenericDetailsFragment
import com.alex.phom.view.fragment.galleryTab.generic.GenericImageFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_generic.*

class GenericSingleCardActivity : RootActivity<GenericSingleCardPresenter.View>(), GenericSingleCardPresenter.View {


    companion object {
        const val GENERIC_CARD = "GENERIC_CARD"
        const val GENERIC_HERO_REFERENCE = "GENERIC_HERO_REFERENCE"
    }

    private val genericFragmentAdapter: GenericCardPageAdapter = GenericCardPageAdapter(supportFragmentManager)

    override val progress: View by lazy { genericProgressView }
    override val presenter: GenericSingleCardPresenter by instance()
    override val layoutResourceId: Int = R.layout.activity_generic
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<GenericSingleCardPresenter>() with provider {
            GenericSingleCardPresenter(
                    view = this@GenericSingleCardActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        genericRecyclerTabLayout.setIndicatorColor(R.color.colorSelected)
        genericRecyclerTabLayout.setBackgroundResource(R.color.colorBlackSource)
        genericRecyclerTabLayout.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    override fun initializeFragmentAdapter(card: CardView, heroReference: List<CardView>) {
        val referenceBundle = mutableListOf<CardView>()
        referenceBundle.add(card)
        referenceBundle.addAll(heroReference)
        genericFragmentAdapter.addFragment(fragment = GenericImageFragment.newInstance(card), title = "Overview")
        genericFragmentAdapter.addFragment(fragment = GenericDetailsFragment.newInstance(referenceBundle), title = "Details")
        genericViewPager.adapter = genericFragmentAdapter
        genericRecyclerTabLayout.setUpWithViewPager(genericViewPager)

    }


    override fun registerListeners() {

    }

    override fun getCard(): CardView {
        return intent.getParcelableExtra(GENERIC_CARD)
    }

    override fun getHeroReference(): List<CardView> {
        val auxList: ArrayList<CardView> = intent.getParcelableArrayListExtra(GENERIC_HERO_REFERENCE)
        return auxList.toList()
    }
}