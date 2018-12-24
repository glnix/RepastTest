package ru.goryachev.repast.feature.feed.domain

import java.io.Serializable

data class RestaurantEntity(val id: Int,
                      val title: String,
                      val address: String,
                      val photoUrl: String,
                      val desc: String, val phone: String,
                      val rating: Float, val ratingCount: Int,
                      val waiters: List<WaiterEntity>) : Serializable

data class WaiterEntity(val id: Int,
                  val name: String,
                  val photoUrl: String,
                  val post: String) : Serializable