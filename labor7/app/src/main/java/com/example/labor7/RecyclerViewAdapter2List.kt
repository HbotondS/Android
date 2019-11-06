package com.example.labor7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter2List : RecyclerView.Adapter<RecyclerViewAdapter2List.ViewHolder> {

    private var context: Context
    private var names: ArrayList<String>
    private var dates: ArrayList<String>

    constructor(context: Context, names: ArrayList<String>, dates: ArrayList<String>) : super() {
        this.context = context
        this.names = names
        this.dates = dates
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt.text = names[position]
        holder.dateTxt.text = dates[position]
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var nameTxt: TextView
        var dateTxt: TextView

        constructor(itemView: View) : super(itemView) {
            this.nameTxt = itemView.findViewById(R.id.nameTxt)
            this.dateTxt = itemView.findViewById(R.id.dateTxt)
        }
    }
}