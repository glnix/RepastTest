package ru.goryachev.repast.feature.global.presentation.presenter

import android.support.annotation.VisibleForTesting
import com.arellomobile.mvp.MvpPresenter
import ru.goryachev.repast.feature.global.presentation.view.BaseView
import ru.goryachev.repast.model.system.flow.FlowRouter
import ru.goryachev.repast.model.ui.rx.LifecycleProvider
import ru.goryachev.repast.model.ui.rx.LifecycleTransformer

abstract class Presenter<V : BaseView>(private val router: FlowRouter) : MvpPresenter<V>() {

    private val provider = LifecycleProvider()
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <T> lifecycle(): LifecycleTransformer<T, T> = provider.lifecycle()

    override fun onDestroy() {
        provider.unsubscribe()
        super.onDestroy()
    }

    open fun onBackPressed() {
        router.back()
    }
}