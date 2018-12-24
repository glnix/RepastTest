package ru.goryachev.repast.feature.splash.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import ru.goryachev.repast.Screens
import ru.goryachev.repast.feature.global.presentation.RxError
import ru.goryachev.repast.feature.global.presentation.presenter.Presenter
import ru.goryachev.repast.feature.splash.presentation.view.SplashView
import ru.goryachev.repast.model.system.flow.FlowRouter
import ru.goryachev.repast.model.system.rx.SchedulersProvider
import ru.goryachev.repast.model.system.rx.subscribe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(private val router: FlowRouter,
                                          private val schedulersProvider: SchedulersProvider) : Presenter<SplashView>(router) {

    companion object {

        private const val TIME_LOADING = 1000L
    }

    override fun onFirstViewAttach() {
        Observable.timer(TIME_LOADING, TimeUnit.MILLISECONDS, schedulersProvider.computation())
                .observeOn(schedulersProvider.main())
                .compose(lifecycle())
                .subscribe({ router.startNewRootFlow(Screens.FLOW_MAIN) }, RxError.doNothing())
    }

    override fun onBackPressed() {}
}