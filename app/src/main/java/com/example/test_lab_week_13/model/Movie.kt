package com.example.test_lab_week_13.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class Movie(
    @PrimaryKey // Menjadikan ID sebagai Primary Key
    val id: Int, // Pindahkan id ke atas agar lebih rapi (opsional, tapi umum di Room)

    val adult: Boolean = false,
    val backdrop_path: String? = "", // Modul Part 2 Step 11: Cegah crash constraint

    @field:Json(name = "original_language")
    val originalLanguage: String? = null,

    @field:Json(name = "original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Float = 0f,

    @field:Json(name = "poster_path")
    val posterPath: String? = null,

    @field:Json(name = "release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean = false,

    @field:Json(name = "vote_average")
    val voteAverage: Float = 0f,

    @field:Json(name = "vote_count")
    val voteCount: Int = 0
)