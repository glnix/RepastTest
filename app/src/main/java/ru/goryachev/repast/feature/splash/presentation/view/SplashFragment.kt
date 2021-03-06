package ru.goryachev.repast.feature.splash.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.goryachev.repast.R
import ru.goryachev.repast.di.DI
import ru.goryachev.repast.di.moduleFlow
import ru.goryachev.repast.feature.global.FlowFragment
import ru.goryachev.repast.feature.splash.presentation.presenter.SplashPresenter
import ru.goryachev.repast.model.system.flow.FlowNavigator
import ru.goryachev.repast.model.system.flow.GlobalRouter
import toothpick.Toothpick

class SplashFragment : FlowFragment(), SplashView {

    companion object {
        fun newInstance(): SplashFragment {
            return SplashFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override val layoutRes: Int = R.layout.fmt_splash

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter {
        return Toothpick.openScopes(DI.SCOPE_APP, scopeName).moduleFlow {
            bind(SplashPresenter::class.java)
        }.getInstance(SplashPresenter::class.java).also {
            Toothpick.closeScope(scopeName)
        }
    }

    override fun provideNavigator(router: GlobalRouter): FlowNavigator = object : FlowNavigator(this, router) {
        override fun createFragment(screenKey: String?, data: Any?): Fragment? = null
    }
}