package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @ColumnInfo(name = "title")
    var titles: String,
    @ColumnInfo(name = "description")
    var descriptions: String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val fullTask get() = "${titles} ${descriptions}"
    override fun toString() = fullTask
}