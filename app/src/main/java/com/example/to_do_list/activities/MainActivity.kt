package com.example.to_do_list.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.R
import com.example.to_do_list.adapters.TaskAdapter
import com.example.to_do_list.data.Task
import com.example.to_do_list.data.TaskDAO
import com.example.to_do_list.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var taskDAO: TaskDAO

    lateinit var taskList: List<Task>

    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*val taskDAO = TaskDAO(this)
        val task = Task(-1L, "Buy Milk", false)
        taskDAO.insert(task)

        val task2 = taskDAO.findById(5)!!
        task2.done = true

        taskDAO.update(task2)*/

        taskDAO = TaskDAO(this)

        //taskList = taskDAO.findAll()

        adapter = TaskAdapter(emptyList(), ::editTask, ::deleteTask, ::checkTask)

        supportActionBar?.title = "My To Do List"

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.addTaskButton.setOnClickListener {
            val intent = Intent (this, TaskActivity::class.java)
            startActivity(intent)


        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    fun refreshData() {
        taskList = taskDAO.findAll()
        adapter.updateTasks(taskList)

    }

    fun checkTask (position: Int) {
        val task = taskList[position]

        task.done = !task.done
        taskDAO.update(task)
        refreshData()
    }

    fun editTask (position: Int) {
        val task = taskList[position]

        val intent = Intent (this, TaskActivity::class.java)
        intent.putExtra(TaskActivity.TASK_ID, task.id)
        startActivity(intent)

    }

    fun deleteTask(position: Int) {
        val task = taskList[position]

        AlertDialog.Builder(this)
            .setTitle("Delete Task" )
            .setMessage("Are you sure you want to delete: ${task.title}?") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.

            .setPositiveButton(
                android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    // Continue with delete operation
                    taskDAO.delete(task)

                    refreshData() }) // A null listener allows the button to dismiss the dialog and take no further action.

            .setNegativeButton(android.R.string.cancel, null)
            .setIconAttribute(android.R.attr.alertDialogIcon)
            .show()

    }
}