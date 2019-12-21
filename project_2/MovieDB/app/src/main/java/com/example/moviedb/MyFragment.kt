package com.example.moviedb

import androidx.fragment.app.Fragment

abstract class MyFragment : Fragment() {
    abstract val type: ViewType
}