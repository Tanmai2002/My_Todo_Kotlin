package com.dazz.mytodo

import android.app.Application
import com.dazz.mytodo.database.Task
import com.dazz.mytodo.database.TaskDatabase

class TaskApplication : Application() {
    val database: TaskDatabase by lazy { TaskDatabase.getDatabase(this) }
}