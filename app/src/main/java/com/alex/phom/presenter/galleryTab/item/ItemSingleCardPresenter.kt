package com.alex.phom.presenter.galleryTab.item

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView
import com.alex.phom.presenter.Presenter

class ItemSingleCardPresenter(view: View, errorHandler: ErrorHandler) : Presenter<ItemSingleCardPresenter.View>(view = view, errorHandler = errorHandler) {

    private var itemCard = CardView()
    override fun initialize() {
        view.showProgress()
        itemCard = view.getCard()
        view.initializeFragmentAdapter(itemCard)
        view.hideProgress()
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    interface View : Presenter.View {
        fun getCard(): com.alex.phom.models.CardView
        fun initializeFragmentAdapter(card: CardView)
    }

}