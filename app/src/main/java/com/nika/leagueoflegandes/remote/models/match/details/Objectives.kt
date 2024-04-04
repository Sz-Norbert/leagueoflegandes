package com.nika.leagueoflegandes.remote.models.match.details

data class Objectives(
    val baron: Baron,
    val champion: Champion,
    val dragon: Dragon,
    val horde: Horde,
    val inhibitor: Inhibitor,
    val riftHerald: RiftHerald,
    val tower: Tower
)