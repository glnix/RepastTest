package ru.goryachev.repast.feature.feed.presentation.detail.view

import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import ru.goryachev.repast.feature.global.presentation.view.BaseView

interface FeedView : BaseView {
    fun showRestaurants(Any: List<Any>)
}