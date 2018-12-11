package com.alex.phom.view.adapter.recyclerViews

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.NewsCardView
import com.alex.phom.view.adapter.RootAdapter
import kotlinx.android.synthetic.main.adapter_individual_article.view.*

class NewsAdapter(onItemClickListener: (NewsCardView) -> Unit) : RootAdapter<NewsCardView>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.adapter_individual_article

    override fun viewHolder(view: View): RootViewHolder<NewsCardView> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<NewsCardView>(itemView = view) {
        override fun bind(model: NewsCardView) {
            itemView.newsCardText.text = model.title
            itemView.newsCardImage.load(model.resourceIMG)

        }
    }
}