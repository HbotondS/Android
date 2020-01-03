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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.BuildConfig
import com.example.moviedb.R
import com.example.moviedb.adapters.ImagesAdapter
import com.example.moviedb.api.Client
import com.example.moviedb.api.Service
import com.example.moviedb.models.Image
import com.example.moviedb.models.ImageResponse
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

    private fun init() {
        myView.findViewById<TextView>(R.id.title).text = movie?.originalTitle
        myView.findViewById<TextView>(R.id.description).text = movie?.overview

        val service = Client.getInstance().create(Service::class.java)
        getVideo(service)
        getImages(service)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun getVideo(service: Service) {
        val call = service.getVideo(movie?.id!!, BuildConfig.API_KEY)
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                val videos = response.body()?.results
                Log.d(TAG, "Loading" + videos.toString())
                videos?.forEach {
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

    private fun getImages(service: Service) {
        val call = service.getImages(movie?.id!!, BuildConfig.API_KEY)
        call.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                val images = response.body()?.backdrops

                initRecyclerView(images!!)
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.d(TAG, "Error during fetching data.")
            }
        })
    }

    private fun initRecyclerView(images: List<Image>) {
        val recyclerView = myView.findViewById<RecyclerView>(R.id.images)
        val layoutManager = LinearLayoutManager(context!!)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        recyclerView.adapter = ImagesAdapter(context!!, images)
    }
}