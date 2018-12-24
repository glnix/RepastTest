package ru.goryachev.repast.feature.feed.data

import io.reactivex.Single
import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import javax.inject.Inject

class FeedRepository @Inject constructor(private val api: Api) {

    fun getRestourantsFeed(): Single<List<RestaurantEntity>> {
        return api.getFeedData()
                .map { it.map { it.toEntity() } }
    }
}