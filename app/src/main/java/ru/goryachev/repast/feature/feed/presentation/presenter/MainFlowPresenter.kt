package ru.goryachev.repast.feature.feed.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import ru.goryachev.repast.R
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

    fun onNavigationChanged(itemId: Int) {
        when(itemId) {
            R.id.review -> router.navigateTo(Screens.SCREEN_FEED)
            R.id.favorites -> router.navigateTo(Screens.SCREEN_MY_FEED)
            R.id.profile -> router.navigateTo(Screens.SCREEN_PROFILE)
        }
    }
}