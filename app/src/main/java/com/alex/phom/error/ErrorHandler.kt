package com.alex.phom.error

/**
 * ErrorHandler.
 */
interface ErrorHandler {
    fun convert(e: Exception): String
}