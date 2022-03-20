package becerra.paul.digimind.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import becerra.paul.digimind.R

class TaskAdapter: BaseAdapter {
    lateinit var context: Context
    var tasks: ArrayList<Task> = ArrayList<Task>()

    constructor(context: Context, tasks: ArrayList<Task>){
        this.context = context
        this.tasks = tasks;
    }

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(p0: Int): Any {
        return tasks[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.task_view, null)

        var task = tasks[p0]

        val textViewTitle: TextView = view.findViewById(R.id.tv_title)
        val textViewTime: TextView = view.findViewById(R.id.tv_time)
        val textViewDays: TextView = view.findViewById(R.id.tv_days)

        textViewTitle.setText(task.title)
        textViewTime.setText(task.time)
        textViewDays.setText(task.day)

        return view
    }
}