package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.inappfragments.DescriptionFragment
import com.example.moviedb.models.Movie
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.Utils

class RelatedContentAdapter(
    private var context: Context,
    private var movies: List<Movie>,
    private var activity: FragmentActivity?
) : RecyclerView.Adapter<RelatedContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recommendation_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(Constants.BASE_IMAGE_URL + movies[position].backdropPath)
            .into(holder.image)
        holder.title.text = movies[position].originalTitle

        holder.itemView.setOnClickListener {
            Utils.startFragment(
                activity?.supportFragmentManager,
                R.id.layoutHolder,
                DescriptionFragment(movies[position])
            )
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}