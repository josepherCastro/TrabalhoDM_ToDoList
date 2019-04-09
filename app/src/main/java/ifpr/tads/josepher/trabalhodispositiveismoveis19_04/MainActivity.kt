package ifpr.tads.josepher.trabalhodispositiveismoveis19_04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db.AppDatabase
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db.dao.TaskDao
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.ui.TaskAdapter
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.ui.TaskAdapterListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_task.*

class MainActivity : AppCompatActivity(), TaskAdapterListener {

    lateinit var taskDao: TaskDao
    lateinit var adapter: TaskAdapter
    var taskEditing: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task.tb").allowMainThreadQueries().build()
        taskDao = db.taskDao()

        fab_AddTask.setOnClickListener {


            //            rc_task = findViewById < RecyclerView > ( R.id.rc_tasks).apply {
//                // use essa configuração para melhorar o desempenho se você souber que as alterações
//                // no conteúdo não altera o tamanho do layout do RecyclerView
//                setHasFixedSize ( true )
//
//                // use um gerenciador de layout linear
//                layoutManager = viewManager
//
//                // especifica um viewAdapter (veja também o próximo exemplo)
//                adapter = viewAdapter
//
//            }
        }
    }

    private fun saveTask() {
        val title = txt_title.text.toString()
        val description = txt_description.text.toString()

        if (taskEditing != null){
            taskEditing?.let {task ->
                task.titles = title
                task.descriptions = description
                taskDao.update(task)
                adapter.upDateTask(task)
            }
        }else{
            var task = Task(title, description)
            var id = taskDao.insert(task).toInt()
            task = taskDao.findById(id)!!

            val position = adapter.addTask(task)
            rc_tasks.scrollToPosition(position)

        }
        clear()
    }

    override fun taskRemoved(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun taskClicked(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadData(){
        val task = taskDao.getAll()
        adapter = TaskAdapter(task.toMutableList(), this)
        rc_tasks.adapter = adapter
        rc_tasks.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
    }
    private fun clear(){
        txt_title.text.clear()
        txt_description.text.clear()

        txt_title.requestFocus()
    }
}
