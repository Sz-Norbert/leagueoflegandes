package com.nika.leagueoflegandes.retrofit

import com.nika.leagueoflegandes.remote.SummonerResponse
import com.nika.leagueoflegandes.util.Util.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolApi {

    @GET("summoner/v4/summoners/by-name/{abc}")
    suspend fun getSum(
        @Path("abc") name: String ,
        @Query("api_key") api_key: String = API_KEY
    ):Response<SummonerResponse>
}