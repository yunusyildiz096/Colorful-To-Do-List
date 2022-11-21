package com.example.todolist.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.model.ToDo
import com.example.todolist.databinding.ToDoListItemBinding
import com.example.todolist.util.extension.remove
import com.example.todolist.util.extension.setSafeOnClickListener
import com.example.todolist.util.extension.show

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.TestAdapterViewHolder>() {

    var onItemDelete: (ToDo) -> Unit = {}
    var onItemClick: (ToDo) -> Unit = {}

    private val diffUtil = object : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo) = oldItem == newItem
    }

    private val recyclerDiffer = AsyncListDiffer(this, diffUtil)
    var toDoList: List<ToDo>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapterViewHolder {
        return TestAdapterViewHolder(
            ToDoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TestAdapterViewHolder, position: Int) {
        holder.bind(toDoList[position])

        holder.itemView.setSafeOnClickListener {
            onItemClick(toDoList[position])
        }
    }

    inner class TestAdapterViewHolder(private var binding: ToDoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo) = with(binding) {
            titleItem.text = toDo.title
            descriptionItem.text = toDo.description
            date.text = toDo.date

            when (toDo.priority) {
                1 -> toDoDeleteItem.setBackgroundResource(R.drawable.delete_note_back_ground_red)
                2 -> toDoDeleteItem.setBackgroundResource(R.drawable.delete_note_back_ground_purple)
                3 -> toDoDeleteItem.setBackgroundResource(R.drawable.delete_note_back_ground_orange)
                4 -> toDoDeleteItem.setBackgroundResource(R.drawable.delete_note_back_ground_blue)
            }

            toDoDeleteItem.setSafeOnClickListener {
                onItemDelete(toDo)
                toDoDeleteItem.setImageResource(R.drawable.ic_baseline_check_24)
            }

                if (toDo.description.toString().isEmpty()){
                    descriptionItem.remove()
                }else{
                    descriptionItem.show()
                }

        }


    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}
