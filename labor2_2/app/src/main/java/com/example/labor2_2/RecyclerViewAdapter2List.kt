package com.example.labor2_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter2List : RecyclerView.Adapter<RecyclerViewAdapter2List.ViewHolder> {

    private var fnames: ArrayList<String>
    private var lnames: ArrayList<String>
    private var courses: ArrayList<String>
    private var context: Context

    constructor(context: Context, fnames: ArrayList<String>, lnames: ArrayList<String>, courses: ArrayList<String>) {
        this.fnames = fnames
        this.lnames = lnames
        this.courses = courses
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.students_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fnames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fname.text = fnames[position]
        holder.lname.text = lnames[position]
        holder.course.text = courses[position]
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var fname: TextView
        var lname: TextView
        var course: TextView

        constructor(itemView: View) : super(itemView) {
            fname = itemView.findViewById(R.id.fnameTxt)
            lname = itemView.findViewById(R.id.lnameTxt)
            course = itemView.findViewById(R.id.courseTxt)
        }
    }
}