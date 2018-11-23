package com.alex.phom.view.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.Article
import com.alex.phom.presenter.ArticlePresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.header_bar.view.*

class ArticleActivity : RootActivity<ArticlePresenter.View>(), ArticlePresenter.View {

    companion object {
        const val ARTICLE_URL = "ARTICLE_URL"
    }

    override val progress: View by lazy { progressView }
    override val presenter: ArticlePresenter by instance()
    override val layoutResourceId: Int = R.layout.activity_article
    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ArticlePresenter>() with provider {
            ArticlePresenter(
                    view = this@ArticleActivity,
                    errorHandler = instance(),
                    getArticleUseCase = instance()
            )
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initializeUI() {
        home_header.button_back.visibility = View.VISIBLE
        articleText.setBackgroundColor(Color.TRANSPARENT)
        articleText.settings.javaScriptEnabled = true
        articleText.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                articleText.loadUrl("javascript:(function() { " +
                        "document.body.style.setProperty(\"color\", \"white\");" +
                        "Array.from(document.getElementsByTagName(\"IMG\")).forEach(function(item) { item.style.width = '0px'; });" +
                        "})()"
                )
            }
        }
    }

    override fun registerListeners() {

    }

    override fun showArticle(article: Article) {
        articleText.loadData(article.post_text, "text/html", "UTF-8")
        articleImage.load(article.post_image)
        articleTitle.text = article.post_title
        articleDate.text = article.post_date

    }

    override fun getArticleUrl(): String {
        return intent.getStringExtra(ARTICLE_URL)
    }


}