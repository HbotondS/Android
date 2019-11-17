package com.example.user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class Adapter2Votes : RecyclerView.Adapter<Adapter2Votes.ViewHolder> {

    private var context: Context
    private var names: ArrayList<String>
    private var votes: ArrayList<String>

    constructor(context: Context, names: ArrayList<String>, votes: ArrayList<String>) : super() {
        this.context = context
        this.names = names
        this.votes = votes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vote_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = names[position]
        holder.vote.text = votes[position]
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var name: TextView
        var vote: TextView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.name)
            this.vote = itemView.findViewById(R.id.vote)
        }
    }
}