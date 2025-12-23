package com.example.test_lab_week_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_13.api.MovieService
import com.example.test_lab_week_13.database.MovieDatabase // Import Database
import com.example.test_lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase // Tambahkan parameter ini
) {
    private val apiKey = "02e464701823dfa0e710aba4ae1ad5ef" // Gunakan API Key Anda

    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            // 1. Cek data di database lokal (Room)
            val movieDao = movieDatabase.movieDao()
            val savedMovies = movieDao.getMovies()

            // 2. Jika data kosong, ambil dari Internet (API)
            if (savedMovies.isEmpty()) {
                try {
                    val popularMovies = movieService.getPopularMovies(apiKey)
                    val movies = popularMovies.results

                    // 3. Simpan data dari API ke Room
                    movieDao.addMovies(movies)

                    // 4. Kirim data ke ViewModel
                    emit(movies)
                } catch (e: Exception) {
                    // Jika error jaringan dan data lokal kosong, emit list kosong atau handle error
                    emit(emptyList())
                    // e.printStackTrace()
                }
            } else {
                // 5. Jika data lokal ada, pakai itu (Offline Mode)
                emit(savedMovies)
            }
        }.flowOn(Dispatchers.IO)
    }
}