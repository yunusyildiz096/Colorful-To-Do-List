package com.example.todolist.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.model.ToDo
import com.example.todolist.databinding.FragmentAddToDoBinding
import com.example.todolist.util.delegate.viewBinding
import com.example.todolist.util.extension.remove
import com.example.todolist.util.extension.setSafeOnClickListener
import com.example.todolist.util.extension.show
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddToDoFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentAddToDoBinding::bind)
    private val viewModel: AddToDoViewModel by viewModels()
    var priority = 1
    var checkColor = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save()
        filterColor()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_to_do, container, false)
    }

    private fun ImageView.filterColorSave(priority: Int, checkColor: Int) = setOnClickListener {
        colorFilter()
        this@AddToDoFragment.priority = priority
        this.setImageResource(R.drawable.ic_baseline_check_24)
        this@AddToDoFragment.checkColor = checkColor
    }

    private fun filterColor() = with(binding) {
        redColorIv.filterColorSave(1, 1)
        purpleColorIv.filterColorSave(2, 2)
        orangeColorIv.filterColorSave(3, 3)
        blueColorIv.filterColorSave(4, 4)
    }

    private fun colorFilter() = with(binding) {
        purpleColorIv.setImageResource(R.drawable.purple_color_filter)
        orangeColorIv.setImageResource(R.drawable.orange_color_filter)
        blueColorIv.setImageResource(R.drawable.blue_color_filter)
        redColorIv.setImageResource(R.drawable.red_color_filter)
    }


    private fun save() = with(binding) {

        saveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addToDoFragment_to_homeFragment)
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val sdf = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            val date = sdf.format(Date())

            if (title.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Please Enter title and description",
                    Toast.LENGTH_SHORT
                ).show()
                saveBtn.remove()
            } else {
                saveBtn.show()
                viewModel.save(
                    ToDo(
                        title = title,
                        description = description,
                        date = date,
                        priority = priority,
                        checkColor = checkColor
                    )
                )
            }
        }

    }
}