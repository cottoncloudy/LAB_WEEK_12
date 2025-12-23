package com.example.test_lab_week_13.model

import androidx.room.Entity // WAJIB ADA
import androidx.room.PrimaryKey // WAJIB ADA
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Tambahkan @Entity di sini agar dikenali sebagai tabel database
@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class Movie(
    // Tambahkan @PrimaryKey pada ID
    @PrimaryKey
    val id: Int = 0,

    val adult: Boolean = false,

    // Berikan default value kosong string agar tidak error constraint saat insert
    val backdrop_path: String? = "",

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