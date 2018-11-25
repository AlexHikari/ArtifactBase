package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.NewsCardView
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(onItemClickListener: (NewsCardView) -> Unit) : RootAdapter<NewsCardView>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.item_news

    override fun viewHolder(view: View): RootViewHolder<NewsCardView> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<NewsCardView>(itemView = view) {
        override fun bind(model: NewsCardView) {
            itemView.newsCardText.text = model.title
            itemView.newsCardImage.load(model.resourceIMG)

        }
    }
}