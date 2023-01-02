package com.e4ekta.moviessearchdemo.models

import com.google.gson.annotations.SerializedName

data class MovieRecordsResponse(
    @SerializedName("page")
    val page: Page
)

data class Page(
    @SerializedName("content-items")
    val contentItems: ContentItems,
    @SerializedName("page-num")
    val pageNum: String,
    @SerializedName("page-size")
    val pageSize: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total-content-items")
    val totalContentItems: String
)

