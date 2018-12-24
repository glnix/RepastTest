package ru.goryachev.repast.feature.feed.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import ru.goryachev.repast.Screens
import ru.goryachev.repast.feature.feed.presentation.view.MainFlowView
import ru.goryachev.repast.feature.global.presentation.presenter.Presenter
import ru.goryachev.repast.model.system.flow.FlowRouter
import javax.inject.Inject

@InjectViewState
class MainFlowPresenter @Inject constructor(private val router: FlowRouter) : Presenter<MainFlowView>(router) {

    override fun onFirstViewAttach() {
        router.newRootScreen(Screens.SCREEN_FEED)
    }
}