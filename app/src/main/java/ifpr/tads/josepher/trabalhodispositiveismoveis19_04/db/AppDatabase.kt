package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.db.dao.TaskDao
import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun taskDao():TaskDao
}