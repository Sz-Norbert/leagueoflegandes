package com.nika.leagueoflegandes.remote.models.match.details

data class Info(
    val gameMode: String,
    val mapId: Int,
    val participants: List<Participant>,
    val teams: List<Team>
)