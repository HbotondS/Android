package com.example.user

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Adapter2Grid : RecyclerView.Adapter<Adapter2Grid.ViewHolder> {

    private val TAG = "RecyclerViewAdapterGrid"

    private val names: Vector<String>
    private val context: Context

    private val listener: (Int) -> Unit

    constructor(
        context: Context,
        names: Vector<String>,
        listener: (Int) -> Unit
    ): super() {
        this.names = names
        this.context = context
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.griditem_layout, parent, false)
        return ViewHolder(layout, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (names[position] == "coffee") {
            Log.d(TAG, "Set Coffee icon")
            holder.imageButton.setImageResource(R.drawable.icons_coffee)
        } else {
            Log.d(TAG, "Set number text icon: " + names[position])
            holder.textView.text = names[position]
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }

    class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        var imageButton: ImageButton
        var textView: TextView
        private var listener: (Int) -> Unit

        constructor(itemView: View, listener: (Int) -> Unit) : super(itemView){

            this.listener = listener

            imageButton = itemView.findViewById(R.id.gridBtn)
            textView = itemView.findViewById(R.id.gridTxt)

            imageButton.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // must override later
            Toast.makeText(v.context, "Button clicked", Toast.LENGTH_LONG).show()

            listener(adapterPosition)
        }
    }
}