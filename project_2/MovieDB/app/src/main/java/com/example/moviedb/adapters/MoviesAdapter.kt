package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.inappfragments.DescriptionFragment
import com.example.moviedb.models.Movie
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils

class MoviesAdapter(
    private var context: Context,
    private var movies: List<Movie>,
    private var activity: FragmentActivity?,
    private var favoriteMovies: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view, activity)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movie = movies[position]
        favoriteMovies.forEach{
            if (it.id == movies[position].id) {
                holder.addedToFavorites = true
            }
        }

        Glide.with(context)
            .load(Constants.BASE_IMAGE_URL + movies[position].posterPath)
            .placeholder(R.drawable.loading)
            .into(holder.poster)
    }

    class ViewHolder(itemView: View, private val activity: FragmentActivity?) :
        RecyclerView.ViewHolder(itemView) {
        var movie: Movie? = null
        var poster = itemView.findViewById<ImageView>(R.id.moviePic)
        var addToFavorites = itemView.findViewById<ImageView>(R.id.addToFavorite)

        var addedToFavorites = false
            set(value) {
                if (value) {
                    addToFavorites.setImageResource(R.drawable.heart_filled)
                    insertIntoFB()
                } else {
                    addToFavorites.setImageResource(R.drawable.heart)
                    removeFromFb()
                }
                field = value
            }

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    Utils.startFragment(activity?.supportFragmentManager, R.id.layoutHolder, DescriptionFragment(movie))
                    Utils.makeSnackBar(itemView, "${movie?.title} was clicked.")
                }
            }

            addToFavorites.setOnClickListener { addedToFavorites = !addedToFavorites }
        }

        private fun insertIntoFB() {
            val userId = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                ?.getString("userid", "")!!
            Constants.myRef4Users.child(userId).child("favorites").child(movie?.id.toString()).setValue(movie)
        }

        private fun removeFromFb() {
            val userId = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                ?.getString("userid", "")!!
            Constants.myRef4Users.child(userId).child("favorites").child(movie?.id.toString()).removeValue()
        }
    }
}