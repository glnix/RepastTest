package ru.goryachev.repast.feature.splash.presentation.view

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.goryachev.repast.R
import ru.goryachev.repast.di.DI
import ru.goryachev.repast.feature.feed.presentation.detail.presenter.ProfilePresenter
import ru.goryachev.repast.feature.global.presentation.view.BaseFragment
import toothpick.Toothpick

class ProfileFragment : BaseFragment() {
    override fun onBackPressed() = presenter.onBackPressed()

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override val layoutRes: Int = R.layout.fmt_profile

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        return Toothpick.openScopes(DI.SCOPE_FLOW_MAIN, scopeName)
                .getInstance(ProfilePresenter::class.java).also {
                    Toothpick.closeScope(scopeName)
                }
    }
}