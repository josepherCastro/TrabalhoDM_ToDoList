package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db.dao

import androidx.room.*
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll():List<Task>
    @Query("SELECT * FROM task WHERE id = :id LIMIT 1")
    fun findById(id:Int):Task?
    @Insert
    fun insert(task: Task):Long
    @Update
    fun update(task: Task)
    @Delete
    fun remove(task: Task)
}