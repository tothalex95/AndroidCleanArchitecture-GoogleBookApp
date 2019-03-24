package hu.miskolc.uni.iit.googlebookapp.domain.usecase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase {

    protected var lastDisposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    protected fun disposeLast() {
        lastDisposable?.run { if (!isDisposed) dispose() }
    }

    fun dispose() {
        compositeDisposable.clear()
    }

}