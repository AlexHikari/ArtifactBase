package com.alex.phom.executor

import com.alex.domain.executor.Executor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * RxExecutor
 */
class RxExecutor : Executor {
    override fun new(): Scheduler = Schedulers.io()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}