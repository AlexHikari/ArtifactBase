package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.models.CardView
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(onItemClickListener: (CardView) -> Unit) : RootAdapter<CardView>(onItemClickListener = onItemClickListener) {
    override val itemLayoutId: Int = R.layout.item_card

    override fun viewHolder(view: View): RootViewHolder<CardView> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<CardView>(itemView = view) {
        override fun bind(model: CardView) {
            itemView.cardImage.loadWithPlaceholder(model.largeImage.imgDef, R.drawable.cardplaceholder)
        }
    }
}