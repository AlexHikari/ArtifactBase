package com.alex.phom.error

import android.content.Context
import com.alex.phom.R

/**
 * AndroidErrorHandler.
 */
class AndroidErrorHandler(val context: Context) : ErrorHandler {
    override fun convert(e: Exception): String =
            when (e) {
                else -> context.getString(R.string.default_error)
            }

}