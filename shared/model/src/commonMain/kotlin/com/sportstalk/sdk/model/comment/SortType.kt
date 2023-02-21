package com.sportstalk.sdk.model.comment

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
enum class SortType(val rawValue: String) {

    @SerialName("oldest")
    Oldest("oldest"),

    @SerialName("newest")
    Newest("newest"),

    @SerialName("likes")
    Likes("likes"),

    @SerialName("votescore")
    VoteScore("votescore"),

    @SerialName("mostreplies")
    MostReplies("mostreplies"),

}