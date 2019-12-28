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
import com.example.moviedb.models.Movie
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils

class MoviesAdapter(
    private var context: Context,
    private var movies: List<Movie>,
    private var activity: FragmentActivity?
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view, activity)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title = movies[position].originalTitle
        holder.id = movies[position].id

        Glide.with(context)
            .load(Constants.BASE_IMAGE_URL + movies[position].posterPath)
            .placeholder(R.drawable.loading)
            .into(holder.poster)
    }

    class ViewHolder(itemView: View, private val activity: FragmentActivity?) :
        RecyclerView.ViewHolder(itemView) {

        var title: String? = null
        var id: Int? = null
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

            addToFavorites.setOnClickListener {
                if (addedToFavorites) {
                    addToFavorites.setImageResource(R.drawable.heart)
                    addedToFavorites = false
                    // todo: remove from favorites
                } else {
                    addedToFavorites = true
                    addToFavorites.setImageResource(R.drawable.heart_filled)
                    insertIntoFB()
                }

            }
        }

        private fun insertIntoFB() {
            val userId = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                ?.getString("userid", "")!!
            Constants.myRef4Users.child(userId).child("favorites").push().child("id").setValue(id)
        }
    }
}