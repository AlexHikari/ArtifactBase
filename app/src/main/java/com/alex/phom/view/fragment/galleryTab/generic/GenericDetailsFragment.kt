package com.alex.phom.view.fragment.galleryTab.generic

import android.os.Bundle
import android.text.Html
import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.*
import com.alex.phom.models.CardTypeView
import com.alex.phom.models.CardView
import com.alex.phom.presenter.galleryTab.generic.GenericDetailsPresenter
import com.alex.phom.view.fragment.RootFragment
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_generic_details.*

class GenericDetailsFragment : RootFragment<GenericDetailsPresenter.View>(), GenericDetailsPresenter.View {


    companion object {
        private const val GENERIC_DETAILS_CARD_LIST_BUNDLE = "GENERIC_DETAILS_CARD_LIST_BUNDLE"

        fun newInstance(references: List<CardView>): GenericDetailsFragment {
            val genericDetailsFragment = GenericDetailsFragment()
            val bundle = Bundle()
            val referenceBundle = ArrayList<CardView>()
            references.forEach { reference ->
                referenceBundle.add(reference)
            }
            bundle.putParcelableArrayList(GENERIC_DETAILS_CARD_LIST_BUNDLE, referenceBundle)
            genericDetailsFragment.arguments = bundle
            return genericDetailsFragment
        }
    }

    override val presenter: GenericDetailsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_generic_details
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<GenericDetailsPresenter>() with provider {
            GenericDetailsPresenter(
                    view = this@GenericDetailsFragment,
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

    override fun getReferences(): List<CardView>? = arguments?.getParcelableArrayList(GENERIC_DETAILS_CARD_LIST_BUNDLE)

    /**
     * Right now there's no hero in the bundle, just because the API
     * @param genericBundleCards List<CardView>
     */
    override fun showCardDetails(genericBundleCards: List<CardView>) {

        val heroCard = genericBundleCards.getFirstHeroCard()
        val genericCard = genericBundleCards.getFirstGenericCard()

        if (genericCard != null) {

            when (genericCard.CardType) {
                CardTypeView.CREEP -> genericIconImage.setImageResource(R.drawable.cardtypecreep)
                CardTypeView.IMPROVEMENT -> genericIconImage.setImageResource(R.drawable.cardtypeimprovement)
                CardTypeView.SPELL -> genericIconImage.setImageResource(R.drawable.cardtypespell)
            }

            genericAbilityText.text = Html.fromHtml(genericCard.cardText.english, Html.FROM_HTML_MODE_COMPACT)
            genericName.text = genericCard.cardName.english
            genericManaCostText.text = genericCard.manaCost.toString()


            // if there's a hero card reference
            if (heroCard != null) {
                GenericHeroReferenceLayout.visibility = View.VISIBLE
                genericHeroIconImage.load(heroCard.heroInGameImage.img)
                genericHeroReferenceNameText.text = heroCard.cardName.english

            } else {
                GenericHeroReferenceLayout.visibility = View.GONE
            }
        }

    }
}
