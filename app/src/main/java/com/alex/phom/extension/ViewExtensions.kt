package com.alex.phom.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * ViewExtensions.
 */
fun ImageView.load(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)
}

/**
 * View
 * */
fun View.hideMe(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.showMe() {
    this.visibility = View.VISIBLE
}