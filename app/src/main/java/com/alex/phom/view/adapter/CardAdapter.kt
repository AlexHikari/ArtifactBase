package com.alex.phom.view.adapter

import android.view.View
import com.alex.phom.R
import com.alex.phom.extension.load
import com.alex.phom.models.Cardview
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(onItemClickListener: (Cardview) -> Unit) : RootAdapter<Cardview>(onItemClickListener = onItemClickListener) {
    override val itemLayoutId: Int = R.layout.item_card

    override fun viewHolder(view: View): RootViewHolder<Cardview> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<Cardview>(itemView = view) {
        override fun bind(model: Cardview) {
            if (model.attack != null && model.attack > 0) {
                itemView.cardAttack.text = model.attack.toString()
            } else {
                itemView.cardAttack.visibility = View.INVISIBLE
            }
            if (model.armor != null && model.armor > 0) {
                itemView.cardArmor.text = model.armor.toString()
            } else {
                itemView.cardArmor.visibility = View.INVISIBLE
            }
            if (model.hitPoints != null && model.hitPoints > 0) {
                itemView.cardHitPoints.text = model.hitPoints.toString()
            } else {
                itemView.cardHitPoints.visibility = View.INVISIBLE
            }
            if (model.cardText.english != null) {
                itemView.cardText.text = model.cardText.english
            } else {
                itemView.cardText.text = "No text provided"
            }
            if (model.largeImage.default != null) {
                itemView.cardImage.load(model.largeImage.default)
            }
        }
    }
}