package com.example.moviedb.inappfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.adapters.MoviesAdapter
import com.example.moviedb.api.Client
import com.example.moviedb.api.Service
import com.example.moviedb.models.Movie
import com.example.moviedb.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : MyFragment() {

    private val TAG = "HomeFragment"

    override val type = ViewType.Home

    private lateinit var myView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.home_layout, container, false)

        init()

        return myView
    }

    private fun init() {
        Log.d(TAG, "Init RecyclerView list")
        recyclerView = myView.findViewById(R.id.topMovies)
        recyclerView.layoutManager = GridLayoutManager(myView.context, 3)
        val movies = ArrayList<Movie>()
        val adapter = MoviesAdapter(context!!, movies)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        loadJSON()
    }

    private fun loadJSON() {
        val service = Client.getInstance().create(Service::class.java)
        val call = service.getPopularMovies(BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()?.results
                recyclerView.adapter = MoviesAdapter(myView.context, movies!!)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "Error during fetching data.")
            }
        })
    }
}
