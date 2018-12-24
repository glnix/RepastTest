package ru.goryachev.repast

import ru.goryachev.repast.feature.global.FlowFragment
import ru.goryachev.repast.feature.feed.presentation.view.MainFlowFragment
import ru.goryachev.repast.feature.splash.presentation.view.SplashFragment

object Screens {

    const val FLOW_SPLASH = "FLOW_SPLASH"

    const val FLOW_MAIN = "FLOW_MAIN"
    const val SCREEN_FEED = "SCREEN_FEED"
    const val SCREEN_MY_FEED = "SCREEN_MY_FEED"


    @Suppress("UNCHECKED_CAST")
    fun getFlowFragment(flowKey: String, data: Any? = null): FlowFragment? {
        return when (flowKey) {
            FLOW_SPLASH -> SplashFragment.newInstance()
            FLOW_MAIN -> MainFlowFragment.newInstance()
            else -> null
        }
    }
}