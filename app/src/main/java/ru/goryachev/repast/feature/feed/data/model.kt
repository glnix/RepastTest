package ru.goryachev.repast.feature.feed.data

data class RestaurantRespose(val id: Int,
                             val title: String?,
                             val address: String?,
                             val photoUrl: String?,
                             val desc: String?, val phone: String?,
                             val rating: Int?,
                             val ratingCount: Int?,
                             val waiters: List<WaiterResponse>?)

data class WaiterResponse(val id: Int,
                          val name: String?,
                          val photoUrl: String?,
                          val post: String?)