package com.sportstalk.sdk.model.reports

import com.sportstalk.sdk.model.CommonParcelable
import com.sportstalk.sdk.model.CommonParcelize

@CommonParcelize
@kotlinx.serialization.Serializable
data class Report(
    val userid: String? = null,
    /** [ReportType] */
    val reason: String? = null
): CommonParcelable

object ReportType {
    const val ABUSE = "abuse"
    const val SPAM = "spam"
}