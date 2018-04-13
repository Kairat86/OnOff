package off.on.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import off.on.R
import off.on.entity.Time
import java.text.SimpleDateFormat
import java.util.*

class OnOffAdapter(private val all: MutableList<Time>) : RecyclerView.Adapter<OnOffAdapter.VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun getItemCount() = all.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.time, parent, false))
    }


    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        val timeText = holder.time
        val context = timeText.context
        val time = all[position]
        timeText.text = context.getString(if (time.on) R.string.switch_on else R.string.switch_off) +
                SimpleDateFormat(" EEE, MMM d, yy, HH:mm").format(Date(time.time))
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView = itemView.findViewById<TextView>(R.id.time)
    }

    fun addItem(time: Time) {
        all.add(time)
    }
}
