package com.alex.phom.view.fragment.galleryTab.item

import android.os.Bundle
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.item.ItemImagePresenter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_item_image.*

class ItemImageFragment : RootFragment<ItemImagePresenter.View>(), ItemImagePresenter.View {


    companion object {
        private const val ITEM_IMAGE_CARD_LIST_BUNDLE = "ITEM_IMAGE_CARD_LIST_BUNDLE"

        fun newInstance(card: CardView): ItemImageFragment {
            val itemImageFragment = ItemImageFragment()
            val bundle = Bundle()
            bundle.putParcelable(ITEM_IMAGE_CARD_LIST_BUNDLE, card)
            itemImageFragment.arguments = bundle
            return itemImageFragment
        }
    }

    override val presenter: ItemImagePresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_item_image
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<ItemImagePresenter>() with provider {
            ItemImagePresenter(
                    view = this@ItemImageFragment,
                    errorHandler = instance()
            )
        }
    }


    override fun initializeUI() {

    }

    override fun registerListeners() {

    }

    override fun showProgress() = itemProgressView.showMe()

    override fun hideProgress() = itemProgressView.hideMe()

    override fun getReferences(): CardView? = arguments?.getParcelable(ITEM_IMAGE_CARD_LIST_BUNDLE)


    override fun showCard(card: CardView) {
        itemCardDisplayImage.loadWithPlaceholder(card.largeImage.imgDef, R.drawable.cardplaceholder)

    }
}