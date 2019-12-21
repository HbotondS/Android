package com.example.moviedb.inappfragments

import androidx.fragment.app.Fragment

abstract class MyFragment : Fragment() {
    abstract val type: ViewType
}