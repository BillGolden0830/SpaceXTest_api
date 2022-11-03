package com.golden.spacextest.model.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SpacexNetwork {
    val spacexAPI: SpacexAPI by lazy{
        initRetrofit()
    }

    private fun initRetrofit(){
        return Retrofit.Builder()
            .baseUrl()
            .client()
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}