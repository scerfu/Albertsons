package com.example.acronymjorge.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lf(
    @Json(name = "freq")
    val freq: Int? = null,
    @Json(name = "lf")
    val lf: String? = null,
    @Json(name = "since")
    val since: Int? = null,
    @Json(name = "vars")
    val vars: List<Var?>? = null
)