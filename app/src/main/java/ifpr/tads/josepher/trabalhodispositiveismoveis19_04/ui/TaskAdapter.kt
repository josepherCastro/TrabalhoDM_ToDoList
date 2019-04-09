package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.R
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task

class TaskAdapter(
    private  var oneTask: MutableList<Task>,
    private  var listener: TaskAdapterListener):
    RecyclerView.Adapter<TaskAdapter.ViewHolder>(){

    fun addTask(task: Task): Int {
        oneTask.add(0, task)
        notifyItemInserted(0)
        return 0
    }
//    fun addTasks(): Int {
//        oneTask
//        notifyItemInserted(0)
//        return 0
//    }
    fun upDateTask(task: Task){
        val position = oneTask.indexOf(task)
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val vh = ViewHolder(view)
        return vh
    }

    override fun getItemCount() = oneTask.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = oneTask[position]
        holder.fillUI(task)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun fillUI(task: Task){
//            itemView.txt_title.text = oneTask.title
//            itemView.txt_description.text = oneTask.description
//            itemView.bt_save.setOnClickListener {
//
//            }
        }
    }
}