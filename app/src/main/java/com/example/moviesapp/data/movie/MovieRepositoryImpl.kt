package com.example.moviesapp.data.movie

import com.example.moviesapp.core.movie.Movie
import com.example.moviesapp.core.movie.MovieRepository
import com.example.moviesapp.data.common.Repository
import com.example.moviesapp.data.network.Api
import com.example.moviesapp.core.common.Result
import com.example.moviesapp.data.network.getDataAsListApi
import com.example.moviesapp.data.network.getData
class MovieRepositoryImpl(private val api: Api, private val movieDao: MovieDao):
    Repository<Movie, MovieEntity>(), MovieRepository {
    override suspend fun getMovieList(id: Int): Result<List<Movie>> {
        val api_keyValue = "f46b58478f489737ad5a4651a4b25079"
        return fetchDataList(
            apiDataProvider = {
                val datos = api.getMovieList(id,api_keyValue)
                datos.body()?.results?.map { it.mapToRoomEntity() }
                    ?.let { movieDao.insertMultiple(it) }
                datos.getDataAsListApi()
            },
            dbDataProvider = {
                movieDao.getMovieList()
            }
        )
    }

    /*override suspend fun getMovieById(id: Int): Result<Movie> {
        return fetchData(
            apiDataProvider = {
                api.getMovieById(id).getData(
                    fetchFromCacheAction = { movieDao.getMovieById(id) },
                    cacheAction = { movieDao.insert(it) }
                )
            },
            dbDataProvider = {
                movieDao.getMovieById(id)
            }
        )
    }*/
}