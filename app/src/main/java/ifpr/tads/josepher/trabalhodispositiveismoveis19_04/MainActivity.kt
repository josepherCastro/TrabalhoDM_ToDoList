package ifpr.tads.josepher.trabalhodispositiveismoveis19_04

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task.db").allowMainThreadQueries().build()
        taskDao = db.taskDao()

        fab_AddTask.setOnClickListener {
            val task =  Task("","", 0)
            val position = adapter.addTask(task)
            rc_tasks.scrollToPosition(position)
        }
        loadData()
    }

    override fun taskRemoved(task: Task) {
        taskDao.remove(task)
        loadData()
    }

    override fun taskClicked(task: Task) {
        val position = adapter.addTaskChange(task)
        rc_tasks.scrollToPosition(position)
    }

    override fun taskSave(task: Task) {
        taskDao.insert(task).toInt()
        loadData()
    }
    override fun taskChange(task: Task) {
        taskDao.update(task)
        loadData()
    }
    override fun share(task: Task) {
        val shareIntent = Intent(Intent.ACTION_SEND)

        with(shareIntent) {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Tarefa Feita")
            putExtra(Intent.EXTRA_TEXT, "Oba! Acabei de concluir: "+task.titles)
        }
        startActivity(shareIntent)
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
