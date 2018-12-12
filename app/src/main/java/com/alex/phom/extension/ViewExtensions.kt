package com.alex.phom.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * ViewExtensions.
 */
fun ImageView.load(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)
}

/**
 * Load the image with a placeholder
 * @receiver ImageView
 * @param url String
 * @param placeholder Int
 */
fun ImageView.loadWithPlaceholder(url: String, placeholder: Int) {
    val options = RequestOptions().placeholder(placeholder)
    Glide.with(this)
            .load(url)
            .apply(options)
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