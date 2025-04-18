package com.example.to_do_list.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.to_do_list.R
import com.example.to_do_list.data.Task
import com.example.to_do_list.data.TaskDAO
import com.example.to_do_list.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    companion object{

        const val TASK_ID = "TASK_ID"
    }

    lateinit var binding: ActivityTaskBinding

    lateinit var taskDAO: TaskDAO

    lateinit var task: Task


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getLongExtra(TASK_ID, -1L)
        taskDAO = TaskDAO(this)

        if (id != -1L) {
            task = taskDAO.findById(id)!!
            binding.taskTitleEditText.setText(task.title)
            supportActionBar?.title = "Edit Task"
        } else {
            task = Task (-1, "")
            supportActionBar?.title = "Create Task"
        }

        binding.saveButton.setOnClickListener {
            val title = binding.taskTitleEditText.text.toString()
            //val task = Task(-1, title)
            task.title = title

        if (task.id != -1L) {
            taskDAO.update(task)
        } else {
            taskDAO.insert(task)
        }

            finish()
        }
    }
}

