package com.alex.phom.view.fragment.galleryTab.item


import android.os.Bundle
import android.text.Html
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardView
import com.alex.phom.models.SubTypeView
import com.alex.phom.presenter.galleryTab.item.ItemDetailsPresenter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : RootFragment<ItemDetailsPresenter.View>(), ItemDetailsPresenter.View {


    companion object {
        private const val ITEM_DETAILS_CARD_LIST_BUNDLE = "ITEM_DETAILS_CARD_LIST_BUNDLE"

        fun newInstance(card: CardView): ItemDetailsFragment {
            val itemDetailsFragment = ItemDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(ITEM_DETAILS_CARD_LIST_BUNDLE, card)
            itemDetailsFragment.arguments = bundle
            return itemDetailsFragment
        }
    }

    override val presenter: ItemDetailsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_item_details
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<ItemDetailsPresenter>() with provider {
            ItemDetailsPresenter(
                    view = this@ItemDetailsFragment,
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

    override fun getReferences(): CardView? = arguments?.getParcelable(ITEM_DETAILS_CARD_LIST_BUNDLE)

    override fun showCardDetails(card: CardView) {

        when (card.subType) {
            SubTypeView.WEAPON -> itemSubtypeIconImage.setImageResource(R.drawable.cardtypeitemweapon)
            SubTypeView.CONSUMABLE -> itemSubtypeIconImage.setImageResource(R.drawable.cardtypeitemconsumable)
            SubTypeView.ARMOR -> itemSubtypeIconImage.setImageResource(R.drawable.cardtypeitemarmor)
            SubTypeView.ACCESSORY -> itemSubtypeIconImage.setImageResource(R.drawable.cardtypeitemaccesory)
            else -> itemSubtypeIconImage.setImageResource(R.drawable.cardtypeitemweapon)
        }
        itemName.text = card.cardName.english
        if (card.goldCost == 0L) itemCostText.text = "_" else itemCostText.text = card.goldCost.toString()
        itemAbilityText.text = Html.fromHtml(card.cardText.english, Html.FROM_HTML_MODE_COMPACT)
    }
}
