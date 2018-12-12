package com.alex.phom.view.fragment.galleryTab.generic

import android.os.Bundle
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.extension.showMe
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.generic.GenericImagePresenter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_generic_image.*

class GenericImageFragment : RootFragment<GenericImagePresenter.View>(), GenericImagePresenter.View {


    companion object {
        private const val GENERIC_CARD_BUNDLE = "GENERIC_CARD_BUNDLE"

        fun newInstance(card: CardView): GenericImageFragment {
            val genericImageFragment = GenericImageFragment()
            val bundle = Bundle()
            bundle.putParcelable(GENERIC_CARD_BUNDLE, card)
            genericImageFragment.arguments = bundle
            return genericImageFragment
        }
    }

    override val presenter: GenericImagePresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_generic_image
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<GenericImagePresenter>() with provider {
            GenericImagePresenter(
                    view = this@GenericImageFragment,
                    errorHandler = instance()
            )
        }
    }


    override fun initializeUI() {

    }

    override fun registerListeners() {

    }

    override fun showProgress() = genericProgressView.showMe()

    override fun hideProgress() = genericProgressView.hideMe()

    override fun getReferences(): CardView? = arguments?.getParcelable(GENERIC_CARD_BUNDLE)


    override fun showCard(genericCard: CardView) {
        genericCardDisplayImage.loadWithPlaceholder(genericCard.largeImage.imgDef, R.drawable.cardplaceholder)
    }
}