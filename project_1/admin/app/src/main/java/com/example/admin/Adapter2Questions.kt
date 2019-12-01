package com.example.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2Questions : RecyclerView.Adapter<Adapter2Questions.ViewHolder> {

    private var context: Context
    private var names: ArrayList<String>
    private var isActivated = ArrayList<Boolean>()
    private var listener: (Int) -> Unit

    constructor(context: Context, names: ArrayList<String>, isActivated: ArrayList<Boolean>,
                listener: (Int) -> Unit) : super() {
        this.context = context
        this.names = names
        this.isActivated = isActivated
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.question_item_layout, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = names[position]
        holder.mySwitch.isChecked = isActivated[position]
        holder.mySwitch.setOnClickListener {
            val sessionName = context.getSharedPreferences(Utils.MY_PREFS_NAME, Context.MODE_PRIVATE)
                ?.getString("sessionName", "")
            if (holder.mySwitch.isChecked) {
                FireBaseHelper().activateQuestion(sessionName!!, names[position])
            }
        }
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var name: TextView
        var mySwitch: Switch

        constructor(itemView: View, listener: (Int) -> Unit) : super(itemView) {
            this.name = itemView.findViewById(R.id.questionTitle)
            this.mySwitch = itemView.findViewById(R.id.mySwitch)

            itemView.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }
}