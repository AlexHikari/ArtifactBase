package com.alex.phom.presenter

import com.alex.phom.R
import com.alex.phom.error.ErrorHandler
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem

class HomePresenter(view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        val listItems: List<AHBottomNavigationItem> = listOf(
                AHBottomNavigationItem(R.string.home_tab_news, R.drawable.newsicon, R.color.colorBlueSource),
                AHBottomNavigationItem(R.string.home_tab_gallery, R.drawable.galleryicon, R.color.colorRedSource),
                AHBottomNavigationItem(R.string.home_tab_builder, R.drawable.crafticon, R.color.colorGreenSource),
                AHBottomNavigationItem(R.string.home_tab_settings, R.drawable.galleryicon, R.color.colorGreenSource))
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