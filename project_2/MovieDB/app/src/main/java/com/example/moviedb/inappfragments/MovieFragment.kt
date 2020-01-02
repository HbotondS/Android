package com.example.moviedb.inappfragments

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.adapters.MoviesAdapter
import com.example.moviedb.models.Movie
import com.example.moviedb.models.MovieResponse
import com.example.moviedb.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class MovieFragment: Fragment()  {

    private val TAG = "MovieFragment"

    protected lateinit var myView: View
    protected lateinit var recyclerView: RecyclerView

    protected lateinit var favoriteMovies: ArrayList<Movie>

    protected fun loadData() {
        loadFavorites()
    }

    private fun loadFavorites() {
        Constants.myRef4Users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val userId = activity?.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
                    ?.getString("userid", "")!!
                favoriteMovies = ArrayList()
                dataSnapshot.child(userId).child("favorites").children.forEach{
                    favoriteMovies.add(it.getValue(Movie::class.java)!!)
                }
                Log.d(TAG, "Favorites queried")

                loadJSON()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    protected abstract fun loadJSON()

    protected fun loadMovies(call: Call<MovieResponse>) {
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()?.results
                recyclerView.adapter = MoviesAdapter(myView.context, movies!!, activity, favoriteMovies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Error during fetching data.")
            }
        })
    }
}