package ru.goryachev.repast.feature.feed.domain

import io.reactivex.Single
import ru.goryachev.repast.feature.feed.data.FeedRepository
import ru.goryachev.repast.model.system.rx.SchedulersProvider
import javax.inject.Inject

class FeedInteractor @Inject constructor(private val repository: FeedRepository, private val schedulersProvider: SchedulersProvider) {

    fun getRestaurantsFeed(): Single<List<RestaurantEntity>> {
        return repository.getRestourantsFeed()
                .subscribeOn(schedulersProvider.computation())
    }

    fun getResturantsFeedWithLikes(): Single<List<RestaurantEntity>> {
        return repository.getRestourantsFeed()
                .map { unsorted -> return@map unsorted.asSequence().filter { it.waiters.isEmpty() }.sortedBy { it.waiters.count() }.toList() }
                .subscribeOn(schedulersProvider.computation())
    }

}