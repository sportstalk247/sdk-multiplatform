package com.sportstalk.sdk.model.user

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
data class User(
    val kind: String? = null /* "app.user" */,
    val userid: String? = null,
    val handle: String? = null,
    val handlelowercase: String? = null,
    val displayname: String? = null,
    val pictureurl: String? = null,
    val profileurl: String? = null,
    /**
     * [Role]
     */
    val role: String? = null,
    val customtags: List<String>? = null,
    val banned: Boolean? = null,
    val banexpires: String? = null, // ISODate ex. "2020-11-11T11:35:07.657812Z"
    val shadowbanned: Boolean? = null,
    val shadowbanexpires: String? = null,   // ISODate ex. "2020-11-11T11:35:07.657812Z"
    val muted: Boolean? = null,
    val muteexpires: String? = null, // ISODate ex. "2020-11-11T11:35:07.657812Z"
    val moderation: String? = null,   // "unknown"
    val reports: List<UserReport>? = null,
): CommonParcelable {
    object Role {
        const val USER = "user"
        const val MODERATOR = "moderator"
        const val ADMIN = "admin"
    }
}

@CommonParcelize
@Serializable
data class UserReport(
    val userid: String? = null,
    /** [Report.Type] */
    val reason: String? = null,
): CommonParcelable