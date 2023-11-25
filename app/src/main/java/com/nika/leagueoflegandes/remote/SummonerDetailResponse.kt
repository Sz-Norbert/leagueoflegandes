package com.nika.leagueoflegandes.remote

import com.google.gson.annotations.SerializedName

data class SummonerDetailResponse(
    @SerializedName("leagueId")
    val leagueId : String,
    @SerializedName("queueType")
    val queueTypew : String,
    @SerializedName("tier")
    val tier : String,
    @SerializedName("rank")
    val rank : String,
    @SerializedName("leaguePoints")
    val leaguePoints:Int,
    @SerializedName("wins")
    val wins : Int,
    @SerializedName("losses")
    val losses : Int

)