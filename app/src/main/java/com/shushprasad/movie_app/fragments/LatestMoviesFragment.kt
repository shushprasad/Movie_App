package com.shushprasad.movie_app.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shushprasad.movie_app.MovieDetailsActivity
import com.shushprasad.movie_app.R
import com.shushprasad.movie_app.adapter.MovieAdapter
import com.shushprasad.network.api.MovieRepository
import com.shushprasad.network.api.model.Movies


class LatestMoviesFragment  : Fragment() {

    val MOVIE_BACKDROP = "extra_movie_backdrop"
    val MOVIE_POSTER = "extra_movie_poster"
    val MOVIE_TITLE = "extra_movie_title"
    val MOVIE_RATING = "extra_movie_rating"
    val MOVIE_RELEASE_DATE = "extra_movie_release_date"
    val MOVIE_OVERVIEW = "extra_movie_overview"

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_latestmovies, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewLatestMovies = view.findViewById<RecyclerView>(R.id.recyclerViewLatestMovies)

        recyclerViewLatestMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        movieAdapter = MovieAdapter(mutableListOf()) { movies -> showMovieDetails(movies) }
        recyclerViewLatestMovies.adapter = movieAdapter

        MovieRepository.getLatestMovies(
            onSuccess = ::onLatestMoviesFetched,
            onError = {
                Toast.makeText(context, "NO DATA", Toast.LENGTH_SHORT).show()
            }
        )
    }
    private fun onLatestMoviesFetched(movies: MutableList<Movies>) {
        movieAdapter.updateData(movies)
    }

    private fun showMovieDetails(movie: Movies) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }
}