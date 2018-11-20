package com.alex.phom.view.activity

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.NewsCard
import com.alex.phom.presenter.HomePresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : RootActivity<HomePresenter.View>(), HomePresenter.View {

    override val layoutResourceId: Int = R.layout.activity_home

    override val progress: View by lazy { progressView }

    override val presenter: HomePresenter by instance()

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<HomePresenter>() with provider {
            HomePresenter(
                    view = this@HomeActivity,
                    errorHandler = instance(),
                    getNewsUseCase = instance()
            )
        }
    }

    override fun initializeUI() {

    }

    override fun registerListeners() {
        click.setOnClickListener { presenter.onClickClicked() }
    }

    override fun showText(text: String) {
        testText.text = text
    }

    override fun showNews(newsList: List<NewsCard>) {
        //populate it
    }
}