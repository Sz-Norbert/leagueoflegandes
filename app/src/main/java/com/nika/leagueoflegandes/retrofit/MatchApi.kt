package com.nika.leagueoflegandes.retrofit

import com.nika.leagueoflegandes.remote.models.match.MatchListResponse
import com.nika.leagueoflegandes.remote.models.match.details.MatchDetailsResponse
import com.nika.leagueoflegandes.util.Util.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchApi {

    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatches(
        @Path("puuid") puuid: String,
        @Query("api_key") apiKey: String =API_KEY
    ): Response<MatchListResponse>

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun getMatchDetails(
        @Path("matchId") matchId:String,
        @Query("api_key") apiKey: String =API_KEY
    ):Response<MatchDetailsResponse>
}