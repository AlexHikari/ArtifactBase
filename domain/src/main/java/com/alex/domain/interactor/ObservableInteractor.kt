package com.alex.domain.interactor

import com.alex.domain.executor.Executor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * ObservableInteractor.
 */
abstract class ObservableInteractor<T : Any>(private val executor: Executor,
                                             private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    protected fun execute(onNext: (T) -> Unit,
                          onComplete: () -> Unit,
                          onError: (Throwable) -> Unit,
                          observable: Observable<T>): Observable<T> {
        val observableWithSchedulers = observable
                .subscribeOn(executor.new())
                .observeOn(executor.main())

        compositeDisposable.add(observableWithSchedulers
                .subscribeWith(object : DisposableObserver<T>() {
                    override fun onComplete() {
                        onComplete()
                    }

                    override fun onNext(t: T) {
                        onNext(t)
                    }

                    override fun onError(e: Throwable) {
                        onError(e)
                    }

                }))

        return observableWithSchedulers
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
