package com.example.to_do_list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
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

        taskList = taskDAO.findAll()

        adapter = TaskAdapter(taskList) {

        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}