package com.nika.leagueoflegandes.remote.models.summoner


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity("users")
data class SummonerResponse(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("accountId")
    val accountId: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("profileIconId")
    val profileIconId: Int,
    @SerializedName("puuid")
    val puuid: String,
    @SerializedName("revisionDate")
    val revisionDate: Long,
    @SerializedName("summonerLevel")
    val summonerLevel: Int
)