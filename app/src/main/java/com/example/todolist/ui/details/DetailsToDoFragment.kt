package com.example.todolist.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.data.model.ToDo
import com.example.todolist.databinding.FragmentDetailsBinding
import com.example.todolist.util.delegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailsToDoFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val viewModel: DetailsToDoViewModel by viewModels()
    private val args: DetailsToDoFragmentArgs by navArgs()

    var priority = 4
    var checkColor = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save()
        filterColor()
        with(binding) {
            etTitle.setText(args.toDo.title)
            etDescription.setText(args.toDo.description)
            priority = args.toDo.priority!!
            checkColor = args.toDo.checkColor!!

            when (args.toDo.checkColor) {
                1 -> redColorIv.setImageResource(R.drawable.ic_baseline_check_24)
                2 -> purpleColorIv.setImageResource(R.drawable.ic_baseline_check_24)
                3 -> orangeColorIv.setImageResource(R.drawable.ic_baseline_check_24)
                4 -> blueColorIv.setImageResource(R.drawable.ic_baseline_check_24)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private fun ImageView.filterColorSave(priority: Int, checkColor: Int) = setOnClickListener {
        colorFilter()
        this@DetailsToDoFragment.priority = priority
        this@DetailsToDoFragment.checkColor = checkColor
        this.setImageResource(R.drawable.ic_baseline_check_24)
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
            findNavController().navigate(R.id.action_detailsToDoFragment_to_homeFragment)
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
            } else {
                viewModel.save(
                    ToDo(
                        args.toDo.id,
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