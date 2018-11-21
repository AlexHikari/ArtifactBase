package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.models.NewsCard

class NewsAdapter(onItemClickListener: (NewsCard) -> Unit) : RootAdapter<NewsCard>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_news

    override fun viewHolder(view: View): RootViewHolder<NewsCard> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<NewsCard>(itemView = view) {
        override fun bind(model: NewsCard) {

        }
    }
}