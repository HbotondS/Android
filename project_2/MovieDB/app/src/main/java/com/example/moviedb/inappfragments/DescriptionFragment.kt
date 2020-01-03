package com.example.moviedb.inappfragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.api.Client
import com.example.moviedb.api.Service
import com.example.moviedb.models.Movie
import com.example.moviedb.models.VideoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescriptionFragment(
    private var movie: Movie?
) : Fragment() {

    private val TAG = "DescriptionFragment"

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "view created")
        myView = inflater.inflate(R.layout.description_layout, container, false)
        init()

        return myView
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        myView.findViewById<TextView>(R.id.title).text = movie?.originalTitle
        myView.findViewById<TextView>(R.id.description).text = movie?.overview
        val service = Client.getInstance().create(Service::class.java)
        val call = service.getVideo(movie?.id!!, BuildConfig.API_KEY)
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                val movies = response.body()?.results
                Log.d(TAG, "Loading" + movies.toString())
                movies?.forEach {
                    if (it.type == "Trailer") {
                        Log.d(TAG, "Loading url")
                        val displayYoutubeVideo = myView.findViewById<WebView>(R.id.video)
                        displayYoutubeVideo!!.settings?.javaScriptEnabled = true
                        displayYoutubeVideo.loadUrl("https://www.youtube.com/embed/" + it.key)

                    }
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                Log.d(TAG, "Error during fetching data.")
            }
        })
    }
}