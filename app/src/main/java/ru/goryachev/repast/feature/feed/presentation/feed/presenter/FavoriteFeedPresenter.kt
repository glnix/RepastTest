package ru.goryachev.repast.feature.feed.presentation.detail.presenter

import com.arellomobile.mvp.InjectViewState
import ru.goryachev.repast.R
import ru.goryachev.repast.feature.feed.domain.FeedInteractor
import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import ru.goryachev.repast.feature.feed.presentation.adapter.ListItemHeader
import ru.goryachev.repast.feature.feed.presentation.adapter.ListItemQR
import ru.goryachev.repast.feature.feed.presentation.detail.view.FavoriteFeedView
import ru.goryachev.repast.feature.feed.presentation.detail.view.FeedView
import ru.goryachev.repast.feature.global.presentation.ErrorHandler
import ru.goryachev.repast.feature.global.presentation.RxDecor
import ru.goryachev.repast.feature.global.presentation.presenter.Presenter
import ru.goryachev.repast.model.ResourceProvider
import ru.goryachev.repast.model.system.flow.FlowRouter
import ru.goryachev.repast.model.system.rx.SchedulersProvider
import ru.goryachev.repast.model.system.rx.subscribe
import javax.inject.Inject

@InjectViewState
class FavoriteFeedPresenter @Inject constructor(val router: FlowRouter,
                                        private val interactor: FeedInteractor,
                                        private val errorHandler: ErrorHandler,
                                        private val schedulersProvider: SchedulersProvider,
                                        private val resourceProvider: ResourceProvider) : Presenter<FavoriteFeedView>(router) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        getFavoriteRestourants()
    }

    private fun getFavoriteRestourants() {
        interactor.getResturantsFeedWithLikes()
                .observeOn(schedulersProvider.main())
                .compose(lifecycle())
                .compose(RxDecor.loading(viewState))
                .subscribe(this::dispatchRestaurantsResults, errorHandler.proceed(viewState))
    }

    private fun dispatchRestaurantsResults(restaurants: List<RestaurantEntity>) {
        viewState.showRestaurants(getUIItems(restaurants))
    }

    private fun getUIItems(restaurants: List<RestaurantEntity>): List<Any> {
        val title = resourceProvider.getString(R.string.header_favorite)
        val subTitle = resourceProvider.getString(R.string.header_favorite_subtitle)
        val header = ListItemHeader(title, subTitle)
        return mutableListOf<Any>(header).apply {
            addAll(restaurants)
        }
    }

}