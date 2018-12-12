package com.alex.phom.view.adapter.recyclerViews

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.models.CardView
import com.alex.phom.view.adapter.RootAdapter
import kotlinx.android.synthetic.main.adapter_individual_card.view.*

class CardAdapter(onItemClickListener: (CardView) -> Unit) : RootAdapter<CardView>(onItemClickListener = onItemClickListener) {
    override val itemLayoutId: Int = R.layout.adapter_individual_card

    override fun viewHolder(view: View): RootViewHolder<CardView> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<CardView>(itemView = view) {
        override fun bind(model: CardView) {
            itemView.cardImage.loadWithPlaceholder(model.largeImage.imgDef, R.drawable.cardplaceholder)
        }
    }
}