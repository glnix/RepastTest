package ru.goryachev.repast.feature.feed.data

import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import ru.goryachev.repast.feature.feed.domain.WaiterEntity


fun RestaurantRespose.toEntity(): RestaurantEntity {
    return RestaurantEntity(id,
            title.orEmpty(),
            address.orEmpty(),
            photoUrl.orEmpty(),
            desc.orEmpty(),
            phone.orEmpty(),
            rating = rating?.toFloat() ?: 0.0f,
            ratingCount = ratingCount ?: 0,
            waiters = waiters?.map { it.toEntity() } ?: emptyList())
}

private fun WaiterResponse.toEntity(): WaiterEntity {
    return WaiterEntity(id,
            name.orEmpty(),
            photoUrl.orEmpty(),
            post.orEmpty()
    )
}
