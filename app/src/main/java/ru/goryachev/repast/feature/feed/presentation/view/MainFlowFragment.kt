package ru.goryachev.repast.feature.feed.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.goryachev.repast.Screens
import ru.goryachev.repast.di.DI
import ru.goryachev.repast.di.inject
import ru.goryachev.repast.di.moduleFlow
import ru.goryachev.repast.feature.feed.data.FeedRepository
import ru.goryachev.repast.feature.feed.domain.FeedInteractor
import ru.goryachev.repast.feature.feed.presentation.detail.presenter.FeedPresenter
import ru.goryachev.repast.feature.feed.presentation.feed.view.FeedFragment
import ru.goryachev.repast.feature.feed.presentation.presenter.MainFlowPresenter
import ru.goryachev.repast.feature.global.FlowFragment
import ru.goryachev.repast.model.system.flow.FlowNavigator
import ru.goryachev.repast.model.system.flow.GlobalRouter
import toothpick.Toothpick

class MainFlowFragment : FlowFragment(), MainFlowView {

    companion object {
        fun newInstance(): MainFlowFragment {
            return MainFlowFragment().apply {
                arguments = Bundle()
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: MainFlowPresenter

    @ProvidePresenter
    fun providePresenter(): MainFlowPresenter {
        return Toothpick.openScope(DI.SCOPE_FLOW_MAIN).getInstance(MainFlowPresenter::class.java)
    }


    override fun provideNavigator(router: GlobalRouter): FlowNavigator {
        return object : FlowNavigator(fragment = this, router = router) {

            override fun createFragment(screenKey: String?, data: Any?): Fragment? {
                return when (screenKey) {
                    Screens.SCREEN_FEED -> FeedFragment.newInstance()
                    Screens.SCREEN_MY_FEED -> null
                    else -> null
                }
            }
        }
    }

    override fun injectDependencies() {
        Toothpick.openScopes(DI.SCOPE_APP, DI.SCOPE_FLOW_MAIN).moduleFlow {
            bind(FeedRepository::class.java).singletonInScope()
            bind(FeedInteractor::class.java).singletonInScope()
            bind(FeedPresenter::class.java)
        }.inject(this)
    }
}