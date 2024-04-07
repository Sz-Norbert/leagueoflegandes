package com.nika.leagueoflegandes.remote.models.match.details

data class Team(
    val bans: List<Any>,
    val objectives: Objectives,
    val teamId: Int,
    val win: Boolean
)