package com.example.usecasetaskmanager

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usecasetaskmanager.databinding.ActivityMainBinding
import com.example.usecasetaskmanager.presentation.TaskAdapter
import com.example.usecasetaskmanager.presentation.TaskViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecViewAdapter()
        setListeners()
        observeTasks()

    }

    private fun observeTasks() {
        viewModel.tasks.observe(this) { tasks ->
            taskAdapter.submitList(tasks)
        }
    }

    private fun setRecViewAdapter() {
        taskAdapter = TaskAdapter { task ->
            Toast.makeText(baseContext, "Task \"${task.title}\" is pressed", Toast.LENGTH_LONG)
                .show()
        }
        binding.rview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }

    private fun setListeners() {
        binding.tvAdd.setOnClickListener {
            setAddViewVisible(true)
        }
        binding.btAddtask.setOnClickListener {
            val title = binding.etName.text.toString()
            val description = binding.etDesc.text.toString()
            if (title.isNotBlank()) {
                viewModel.addTask(title, description)
                setAddViewVisible(false)
                binding.etName.text.clear()
                binding.etDesc.text.clear()
            }
        }
    }

    private fun setAddViewVisible(b: Boolean) {
        if (b) {
            binding.clAdd.visibility = View.VISIBLE
        } else {
            binding.clAdd.visibility = View.GONE
        }
    }
}