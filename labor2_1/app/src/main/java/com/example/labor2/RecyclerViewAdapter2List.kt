package com.example.labor2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter2List : RecyclerView.Adapter<RecyclerViewAdapter2List.ViewHolder> {

    private var TAG = "RecyclerViewAdapter2List"
    private var hobbies: ArrayList<String>
    private var context: Context

    constructor(context: Context, hobbies: ArrayList<String>) {
        this.context = context
        this.hobbies = hobbies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "Create ViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.hobby_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder is called")

        holder.hobbyText.text = hobbies[position]
    }

    class ViewHolder : RecyclerView.ViewHolder {

        var hobbyText: TextView

        constructor(itemView: View) : super(itemView) {
            hobbyText = itemView.findViewById(R.id.hobbyText)
        }
    }
}