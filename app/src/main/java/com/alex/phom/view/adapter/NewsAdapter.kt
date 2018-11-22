package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.NewsCard
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(onItemClickListener: (NewsCard) -> Unit) : RootAdapter<NewsCard>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_news

    override fun viewHolder(view: View): RootViewHolder<NewsCard> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<NewsCard>(itemView = view) {
        override fun bind(model: NewsCard) {
            itemView.newsCardText.text = model.title
            itemView.newsCardImage.load(model.resourceIMG)

        }
    }
}