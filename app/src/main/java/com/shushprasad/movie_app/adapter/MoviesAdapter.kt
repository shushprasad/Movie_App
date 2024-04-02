package com.shushprasad.movie_app.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shushprasad.movie_app.R
import com.shushprasad.network.api.model.Movies


class MovieAdapter(private var movies: MutableList<Movies>,
                   private val onMovieClick: (movie: Movies) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateData(newData: MutableList<Movies>) {
        movies = newData
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        private val movieTitle: TextView = itemView.findViewById(R.id.textMovieTitle)
        private val movieDate: TextView = itemView.findViewById(R.id.textMoviedate)

        fun bind(movie: Movies) {
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .into(moviePoster)

            movieTitle.text = movie.title
            movieDate.text = "Release Date: "+movie.releaseDate
        }
    }
}
