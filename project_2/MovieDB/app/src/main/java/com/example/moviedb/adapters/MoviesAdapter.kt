package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.models.Movie
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils

class MoviesAdapter(
    private var context: Context,
    private var movies: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title = movies[position].originalTitle

        Glide.with(context)
            .load(Constants.BASE_IMAGE_URL + movies[position].posterPath)
            .placeholder(R.drawable.loading)
            .into(holder.poster)
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: String? = null
        var poster = itemView.findViewById<ImageView>(R.id.moviePic)
        var addToFavorites = itemView.findViewById<ImageView>(R.id.addToFavorite)
        private var addedToFavorites = false

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    // todo: move to details screen
                    Utils.makeSnackBar(itemView, "$title was clicked.")
                }
            }

            addToFavorites.setOnClickListener{
                // todo: store liked movies
                if (addedToFavorites) {
                    addToFavorites.setImageResource(R.drawable.heart)
                    addedToFavorites = false
                } else {
                    addedToFavorites = true
                    addToFavorites.setImageResource(R.drawable.heart_filled)
                }

            }
        }
    }
}