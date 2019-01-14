package ru.goryachev.repast.feature.feed.presentation.detail.presenter

import com.arellomobile.mvp.InjectViewState
import ru.goryachev.repast.R
import ru.goryachev.repast.feature.feed.domain.FeedInteractor
import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import ru.goryachev.repast.feature.feed.presentation.adapter.ListItemHeader
import ru.goryachev.repast.feature.feed.presentation.adapter.ListItemQR
import ru.goryachev.repast.feature.feed.presentation.detail.view.FeedView
import ru.goryachev.repast.feature.global.presentation.ErrorHandler
import ru.goryachev.repast.feature.global.presentation.RxDecor
import ru.goryachev.repast.feature.global.presentation.presenter.Presenter
import ru.goryachev.repast.feature.global.presentation.view.BaseView
import ru.goryachev.repast.model.ResourceProvider
import ru.goryachev.repast.model.system.flow.FlowRouter
import ru.goryachev.repast.model.system.rx.SchedulersProvider
import ru.goryachev.repast.model.system.rx.subscribe
import javax.inject.Inject
import kotlin.math.round

@InjectViewState
class ProfilePresenter @Inject constructor(val router: FlowRouter) : Presenter<BaseView>(router)