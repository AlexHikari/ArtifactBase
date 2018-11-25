package com.alex.phom.view.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.widget.LinearLayoutManager
import com.alex.phom.R
import com.alex.phom.extension.hideMe
import com.alex.phom.extension.showMe
import com.alex.phom.models.ArticleView
import com.alex.phom.models.NewsCardView
import com.alex.phom.navigator.navigateToArticle
import com.alex.phom.presenter.NewsPresenter
import com.alex.phom.view.adapter.NewsAdapter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : RootFragment<NewsPresenter.View>(), NewsPresenter.View {

    companion object {
        fun newInstance(): NewsFragment = NewsFragment()
    }

    private val newsAdapter: NewsAdapter = NewsAdapter {
        presenter.onNewClicked(it)
    }
    override val presenter: NewsPresenter by instance()
    override val layoutResourceId: Int = R.layout.fragment_news
    override val fragmentModule: Kodein.Module = Kodein.Module {
        bind<NewsPresenter>() with provider {
            NewsPresenter(
                    view = this@NewsFragment,
                    errorHandler = instance(),
                    getNewsUseCase = instance()
            )
        }
    }

    override fun initializeUI() {
        news_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    override fun registerListeners() {
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun showNews(newsListView: List<NewsCardView>) {
        newsAdapter.addAll(newsListView.toMutableList())
    }

    override fun navigateToArticle(articleViewList: ArrayList<ArticleView>) {
        context?.let {
            navigateToArticle(it, articleViewList)
        }
    }

    override fun getNetworkInfo(): Boolean {
        val cm: ConnectivityManager
        context?.let {
            cm = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    return true
                }
                if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            }
        }
        return false
    }
}