package com.example.app_ticket.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,

    val title: String,

    val poster_path: String?,

    val overview: String,

    val vote_average: Double
)
