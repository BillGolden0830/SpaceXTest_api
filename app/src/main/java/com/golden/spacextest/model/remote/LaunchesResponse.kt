package com.golden.spacextest.model.remote

import com.squareup.moshi.Json

data class LaunchesResponse (
    val docs: List<LaunchItem>
)

data class LaunchItem(
    val links: LaunchLinks,
    @field:Json(name = "rocket")
    val rocketID: String,
    val success: Boolean,
    val name: String,
    val date_utc: String
)

data class LaunchLinks(
    val patch: LaunchLinksPatch,
    val webcast: String,
    val article: String
)

data class LaunchLinksPatch(
    val small: String,
    val large: String
)

data class CompanyResponse(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: Int,
    val launch_sites: Int,
    val valuation: Long

)

data class RocketResponse(
    val name: String,
    val type: String
)

data class LaunchRocket(
    val launchItem: LaunchItem,
    val rocketResponse: RocketResponse
)

