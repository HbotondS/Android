package com.example.moviedb.inappfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.adapters.MoviesAdapter
import com.example.moviedb.models.Movie
import com.example.moviedb.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FavoritesFragment : MyFragment() {

    private val TAG = "FavoritesFragment"

    override val type = ViewType.Favorites

    private lateinit var myView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.favorites_layout, container, false)

        init()

        return myView
    }

    private fun init() {
        Log.d(TAG, "Init RecyclerView list")
        recyclerView = myView.findViewById(R.id.favoriteMovies)
        recyclerView.layoutManager = GridLayoutManager(myView.context, 3)
        loadFromDB()
    }

    private fun loadFromDB() {
        Constants.myRef4Users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val userid = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                    ?.getString("userid", "")
                var movies = ArrayList<Movie>()
                dataSnapshot.children.forEach { value ->
                    if (value.key == userid) {
                        value.child("favorites").children.forEach{
                            movies.add(it.getValue(Movie::class.java)!!)
                        }
                        recyclerView.adapter = MoviesAdapter(context!!, movies, activity)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}
