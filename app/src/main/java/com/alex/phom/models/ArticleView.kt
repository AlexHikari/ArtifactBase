package com.alex.phom.models

import android.os.Parcel
import android.os.Parcelable


data class ArticleView(
        val post_url: String,
        val post_title: String,
        val post_date: String,
        var post_image: String,
        val post_text: String,
        val selected: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(post_url)
        parcel.writeString(post_title)
        parcel.writeString(post_date)
        parcel.writeString(post_image)
        parcel.writeString(post_text)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleView> {
        override fun createFromParcel(parcel: Parcel): ArticleView {
            return ArticleView(parcel)
        }

        override fun newArray(size: Int): Array<ArticleView?> {
            return arrayOfNulls(size)
        }
    }
}