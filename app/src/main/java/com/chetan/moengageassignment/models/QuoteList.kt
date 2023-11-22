package com.chetan.moengageassignment.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import android.os.Parcelable

@Keep
data class QuoteList(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("lastItemIndex")
    var lastItemIndex: Int = 0,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("results")
    var results: List<Result> = listOf(),
    @SerializedName("totalCount")
    var totalCount: Int = 0,
    @SerializedName("totalPages")
    var totalPages: Int = 0
) {
    @Keep
    data class Result(
        @SerializedName("author")
        var author: String = "",
        @SerializedName("authorSlug")
        var authorSlug: String = "",
        @SerializedName("content")
        var content: String = "",
        @SerializedName("dateAdded")
        var dateAdded: String = "",
        @SerializedName("dateModified")
        var dateModified: String = "",
        @SerializedName("_id")
        var id: String = "",
        @SerializedName("length")
        var length: Int = 0,
        @SerializedName("tags")
        var tags: List<String> = listOf()
    )
}