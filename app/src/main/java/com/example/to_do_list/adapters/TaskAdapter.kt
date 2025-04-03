package com.example.to_do_list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.to_do_list.data.Task
import com.example.to_do_list.databinding.ItemTaskBinding

class TaskAdapter (
    var items: List<Task>,
    val onClick: (Int) -> Unit,
    val onDelete: (Int) -> Unit
) : Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
    //return items.size



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = items[position]
        holder.render(task)
        holder.itemView.setOnClickListener {
            onClick(position)
        }
       holder.binding.deleteButton.setOnClickListener {
           onDelete(position)
       }
    }

    fun updateTasks (newTasks: List<Task>){

        this.items = newTasks
        notifyDataSetChanged()
}

}

class TaskViewHolder (val binding: ItemTaskBinding) : ViewHolder(binding.root){
    //val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    //val pictureImageView: ImageView = view.findViewById(R.id.pictureImageView)

    fun render(task: Task) {
        binding.titleTextView.text = task.title
        binding.doneCheckBox.isChecked = task.done
    }

}