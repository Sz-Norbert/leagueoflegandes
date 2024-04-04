package com.nika.leagueoflegandes.retrofit

import com.nika.leagueoflegandes.remote.models.SummonerDetailResponse
import com.nika.leagueoflegandes.remote.models.SummonerResponse
import com.nika.leagueoflegandes.util.Util.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolApi {


    @GET("by-name/{abc}")
    suspend fun getSum(
        @Path("abc") name: String,
        @Query("api_key") api_key: String = API_KEY
    ): Response<SummonerResponse>

    @GET("by-summoner/{sumId}")
    suspend fun getSumDetail(
        @Path("sumId") sumId:String,
        @Query("api_key") api_key: String = API_KEY

    ):Response<SummonerDetailResponse>
}