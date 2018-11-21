package com.alex.phom.view.activity

import android.view.View
import com.alex.phom.R
import com.alex.phom.presenter.ArticlePresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_news.*

class ArticleActivity : RootActivity<ArticlePresenter.View>(), ArticlePresenter.View {

    override val progress: View by lazy { progressView }
    override val presenter: ArticlePresenter by instance()
    override val layoutResourceId: Int = R.layout.activity_article
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ArticlePresenter>() with provider {
            ArticlePresenter(
                    view = this@ArticleActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {

    }

    override fun registerListeners() {

    }

    override fun showArticle() {

    }


}