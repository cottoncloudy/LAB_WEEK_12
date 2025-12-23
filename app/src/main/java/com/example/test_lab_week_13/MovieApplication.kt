package com.example.test_lab_week_13

import android.app.Application
import com.example.test_lab_week_13.api.MovieService
import com.example.test_lab_week_13.database.MovieDatabase // Import
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApplication : Application() {
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        // 1. Setup Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val movieService = retrofit.create(MovieService::class.java)

        // 2. Setup Database (Singleton)
        val movieDatabase = MovieDatabase.getInstance(applicationContext)

        // 3. Masukkan keduanya ke Repository
        movieRepository = MovieRepository(movieService, movieDatabase)
    }
}