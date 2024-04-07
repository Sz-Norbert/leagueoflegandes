package com.nika.leagueoflegandes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nika.leagueoflegandes.remote.models.summoner.SummonerResponse

@Dao
interface UserDao {


        @Upsert
        suspend fun upsertUser(user: SummonerResponse)

        @Query("SELECT * FROM users WHERE name = :summonorName")
        fun getSummoner(summonorName: String): LiveData<SummonerResponse?>

}