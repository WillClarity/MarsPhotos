package com.example.android.marsphotos.network

import com.squareup.moshi.Json


/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class MarsPhoto(
    @Json(name = "_id") val id: String,
    val name: String,
    @Json(name = "image") val imgSrcUrl: String

)