package com.alex.phom.view.activity

import android.support.v4.content.ContextCompat
import android.view.View
import com.alex.phom.R
import com.alex.phom.presenter.HomePresenter
import com.alex.phom.view.fragment.NewsFragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : RootActivity<HomePresenter.View>(), HomePresenter.View {


    override val layoutResourceId: Int = R.layout.activity_home

    override val progress: View
        get() = TODO("Never use progress")

    override val presenter: HomePresenter by instance()

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HomePresenter>() with provider {
            HomePresenter(
                    view = this@HomeActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        // Nothing to do yet
    }

    override fun registerListeners() {
        // Nothing to do yet
    }

    override fun initializeBottomNavigationView(items: List<AHBottomNavigationItem>) {
        bottom_navigation.addItems(items)
        bottom_navigation.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundDark))
        bottom_navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottom_navigation.currentItem = 0
    }

    override fun showNewsScreen() {
        supportFragmentManager.beginTransaction().replace(R.id.contentView, NewsFragment.newInstance()).commit()
    }
}