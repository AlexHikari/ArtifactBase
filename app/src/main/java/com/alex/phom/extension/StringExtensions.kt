package com.alex.phom.extension

import android.text.Html
import android.text.Spanned


fun String.toHtmlSpanned(): Spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
