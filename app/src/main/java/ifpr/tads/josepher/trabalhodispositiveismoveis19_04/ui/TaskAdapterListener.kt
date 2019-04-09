package ifpr.tads.josepher.trabalhodispositiveismoveis19_04.ui

import ifpr.tads.josepher.trabalhodispositiveismoveis19_04.entities.Task

interface TaskAdapterListener {
    fun taskRemoved(task: Task)
    fun taskClicked(task: Task)
}