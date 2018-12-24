package ru.goryachev.repast.feature.feed.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.goryachev.repast.BuildConfig

interface Api {

    @GET("bins/1cn3gw")
    fun getFeedData(): Single<List<RestaurantRespose>>

}