package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.ui

import android.content.Intent
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.R
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db.dao.TaskDao
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_task.*
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.item_task_compact.view.*

class TaskAdapter(

    private  var oneTask: MutableList<Task>,
    private  var listener: TaskAdapterListener):
        RecyclerView.Adapter<TaskAdapter.ViewHolder>(){

        private var taskEditing : Task? = null

        fun addTask(task: Task): Int {
            taskEditing=task
            oneTask.add(0, task)
            notifyItemInserted(0)
            return 0
        }
        fun addTaskChange(task: Task): Int {
//            taskEditing=task
            oneTask.add(0, task)
            notifyItemInserted(0)
            return 0
        }

        fun upDateTask(task: Task){
            val position = oneTask.indexOf(task)
            notifyItemChanged(position)
        }

        override fun getItemViewType(position: Int): Int {
            val task = oneTask[position]
            if(task== taskEditing){
                return R.layout.item_task
            }else{
                return R.layout.item_task_compact
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder (
                LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
            )

        override fun getItemCount() = oneTask.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val task = oneTask[position]
            holder.fillUI(task)
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun fillUI(task: Task) {
                if (taskEditing == task) {

                    itemView.txt_title.setText(task.titles)
                    itemView.txt_description.setText(task.descriptions)

                    itemView.bt_save.setOnClickListener {
                        if (taskEditing != null) {

                            if (task.id.equals(0)) {
                                taskEditing?.let { task ->
                                    task.titles = itemView.txt_title.text.toString()
                                    task.descriptions = itemView.txt_description.text.toString()


                                    listener.taskSave(task)
                                    val position = oneTask.indexOf(task)
                                    notifyItemInserted(position)
                                }
                            } else {
//                                itemView.bt_delete.visibility = View.GONE

                                taskEditing?.let { task ->
                                    task.titles = itemView.txt_title.text.toString()
                                    task.descriptions = itemView.txt_description.text.toString()

                                    listener.taskChange(task)
                                    val position = oneTask.indexOf(task)
                                    notifyItemChanged(position)
                                }
                            }
                        }
                    }
                    itemView.bt_delete.setOnClickListener {  // apagar da lista e do banco
                        with(this@TaskAdapter){
                            val position = oneTask.indexOf(task)
                            oneTask.removeAt(position)
                            notifyItemRemoved(position)
                            listener.taskRemoved(task)
                        }
                    }
                }else{
                    itemView.txTitle.text = task.titles
                    var mycard = itemView as CardView
                    if (task.status == 0){
                        itemView.bt_share.visibility = View.VISIBLE
                        mycard.setCardBackgroundColor(RED)
                    }else{
                        itemView.bt_share.visibility = View.GONE
                        mycard.setCardBackgroundColor(GREEN)
                    }

//                    itemView.bt_delete.visibility = View.VISIBLE
                    itemView.setOnClickListener {
                        taskEditing = task
                        val position = oneTask.indexOf(task)
                        notifyItemChanged(position)

//                        itemView.bt_delete.visibility = View.VISIBLE
                    }
                    itemView.setOnLongClickListener {
                        if (task.status == 0){
                            task.status = 1
                            listener.taskChange(task)
                            val position = oneTask.indexOf(task)
                            notifyItemChanged(position)
                        }else{
                            task.status = 0
                            listener.taskChange(task)
                            val position = oneTask.indexOf(task)
                            notifyItemChanged(position)
                        }
                        true
                    }
                    itemView.bt_share.setOnClickListener {
                        listener.share(task)
                    }
                }
            }
        }
}