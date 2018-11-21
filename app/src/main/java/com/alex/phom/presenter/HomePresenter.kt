package com.alex.phom.presenter

import com.alex.phom.R
import com.alex.phom.error.ErrorHandler
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem

class HomePresenter(view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        val listItems: List<AHBottomNavigationItem> = listOf(
                AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_launcher_foreground, R.color.colorBlueSource),
                AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_launcher_foreground, R.color.colorRedSource),
                AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_launcher_foreground, R.color.colorGreenSource))
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