package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.loadWithPlaceholder
import com.alex.phom.models.Cardview
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(onItemClickListener: (Cardview) -> Unit) : RootAdapter<Cardview>(onItemClickListener = onItemClickListener) {
    override val itemLayoutId: Int = R.layout.item_card

    override fun viewHolder(view: View): RootViewHolder<Cardview> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<Cardview>(itemView = view) {
        override fun bind(model: Cardview) {
            itemView.cardImage.loadWithPlaceholder(model.largeImage.imgdef, R.drawable.cardplaceholder)
        }
    }
}