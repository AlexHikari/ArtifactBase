package com.alex.phom.view.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.ArticleView
import com.alex.phom.presenter.ArticlePresenter
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.header_bar.view.*

class ArticleActivity : RootActivity<ArticlePresenter.View>(), ArticlePresenter.View {

    companion object {
        const val ARTICLE_BUNDLE = "ARTICLE_BUNDLE"
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

        articleText.setBackgroundColor(Color.TRANSPARENT)
        articleText.settings.javaScriptEnabled = true
        articleText.settings.defaultFontSize = 14
        articleText.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                articleText.loadUrl("javascript:(function() { " +
                        "document.body.style.setProperty(\"color\", \"white\");" +
                        "Array.from(document.getElementsByTagName(\"IMG\")).forEach(function(item) { item.style.maxWidth = '100%'; item.style.height = 'auto' });" +
                        "Array.from(document.getElementsByTagName(\"A\")).forEach(function(item) { item.style.color = 'white' });" +
                        "})()"
                )
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }

        }
    }

    override fun registerListeners() {
        home_header.button_back.setOnClickListener { presenter.onBackButtonClicked() }
    }

    override fun showArticle(articleView: ArticleView, isLast: Boolean, isFist: Boolean) {
        if (isLast) {
            //show right arrow
        }
        if (isFist) {
            // show leftArrow
        }
        articleText.loadData(articleView.post_text, "text/html", "UTF-8")
        articleImage.load(articleView.post_image)
        articleTitle.text = articleView.post_title
        articleDate.text = articleView.post_date

    }

    override fun getArticleUrl(articleViewList: ArrayList<ArticleView>): String {
        var returnedUrl = ""
        articleViewList.forEach {
            if (it.selected)
                returnedUrl = it.post_url
        }
        return returnedUrl
    }

    override fun getArticleList(): ArrayList<ArticleView> {

        return intent.getParcelableArrayListExtra<ArticleView>(ARTICLE_BUNDLE)
    }

    override fun finishActivity() {
        onBackPressed()
    }

    override fun showBack() {
        home_header.button_back.visibility = View.VISIBLE
    }
}