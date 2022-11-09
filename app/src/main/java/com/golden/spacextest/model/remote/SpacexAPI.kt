package com.golden.spacextest.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpacexAPI {
    @POST(ROUTES_FOR_LAUNCHES)
    suspend fun getAllLaunches(): LaunchesResponse
    @GET(ROUTES_FOR_COMPANY)
    suspend fun getCompanyInfo(): Response<CompanyResponse>
    @GET(ROUTES_FOR_ROCKET)
    suspend fun getRocketInfo(
        @Path(ROCKET_ID) rocketId: String
    ): RocketResponse
}