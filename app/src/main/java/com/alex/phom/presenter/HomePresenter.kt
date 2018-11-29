package com.alex.phom.presenter

import com.alex.phom.R
import com.alex.phom.error.ErrorHandler
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem

class HomePresenter(view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        val listItems: List<AHBottomNavigationItem> = listOf(
                AHBottomNavigationItem(R.string.tab_1, R.drawable.newsicon, R.color.colorBlueSource),
                AHBottomNavigationItem(R.string.tab_2, R.drawable.galleryicon, R.color.colorRedSource),
                AHBottomNavigationItem(R.string.tab_3, R.drawable.crafticon, R.color.colorGreenSource),
                AHBottomNavigationItem(R.string.tab_4, R.drawable.galleryicon, R.color.colorGreenSource))
        view.initializeBottomNavigationView(listItems)
        view.showNewsScreen()
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    interface View : Presenter.View {
        fun initializeBottomNavigationView(items: List<AHBottomNavigationItem>)
        fun showNewsScreen()
    }
}