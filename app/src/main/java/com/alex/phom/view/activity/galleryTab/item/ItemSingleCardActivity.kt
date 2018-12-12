package com.alex.phom.view.activity.galleryTab.item

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.item.ItemSingleCardPresenter
import com.alex.phom.view.activity.RootActivity
import com.alex.phom.view.adapter.fragments.ItemCardPageAdapter
import com.alex.phom.view.fragment.galleryTab.item.ItemDetailsFragment
import com.alex.phom.view.fragment.galleryTab.item.ItemImageFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_item.*

class ItemSingleCardActivity : RootActivity<ItemSingleCardPresenter.View>(), ItemSingleCardPresenter.View {


    companion object {
        const val ITEM_CARD_BUNDLE = "ITEM_CARD_BUNDLE"
    }

    private val itemCardPageAdapter: ItemCardPageAdapter = ItemCardPageAdapter(supportFragmentManager)

    override val progress: View by lazy { itemProgressView }
    override val presenter: ItemSingleCardPresenter by instance()
    override val layoutResourceId: Int = R.layout.activity_item
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ItemSingleCardPresenter>() with provider {
            ItemSingleCardPresenter(
                    view = this@ItemSingleCardActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        itemRecyclerTabLayout.setIndicatorColor(R.color.colorSelected)
        itemRecyclerTabLayout.setBackgroundResource(R.color.colorBlackSource)
        itemRecyclerTabLayout.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    override fun initializeFragmentAdapter(card: CardView) {
        itemCardPageAdapter.addFragment(fragment = ItemImageFragment.newInstance(card), title = "Overview")
        itemCardPageAdapter.addFragment(fragment = ItemDetailsFragment.newInstance(card), title = "Details")
        itemViewPager.adapter = itemCardPageAdapter
        itemRecyclerTabLayout.setUpWithViewPager(itemViewPager)

    }

    override fun registerListeners() {

    }

    override fun getCard(): CardView {
        return intent.getParcelableExtra(ITEM_CARD_BUNDLE)
    }
}