package com.example.todolist.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.util.delegate.viewBinding
import com.example.todolist.util.extension.remove
import com.example.todolist.util.extension.setSafeOnClickListener
import com.example.todolist.util.extension.show
import com.example.todolist.util.extension.snackBarWithAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeToDoViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchToDo()
        filterBtn()
        recyclerEmpty()
        viewModel.getToDoList()

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.noteRecyclerView)

        binding.noteRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    binding.floatingActionButton.remove()
                } else {
                    binding.floatingActionButton.show()
                }
                super.onScrolled(recyclerView, dx, dy)

            }
        })

        viewModel.toDoList.observe(viewLifecycleOwner) {
            adapter.toDoList = it
            binding.noteRecyclerView.adapter = adapter
        }

        adapter.onItemDelete = {
            viewModel.delete(it)
            findNavController().navigate(R.id.action_homeFragment_self)
            requireView().snackBarWithAction("${it.title} Delete", "Take it back") {
                viewModel.save(it)
                findNavController().navigate(R.id.action_homeFragment_self)
            }

        }
        binding.floatingActionButton.setSafeOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddToDoFragment())
        }
        adapter.onItemClick = {
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailsToDoFragment(it)
            Navigation.findNavController(view).navigate(nav)
        }
    }

    private var simpleCallback =
        object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                var startPosition = viewHolder.adapterPosition
                var endPosition = target.adapterPosition
                recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        }

    private fun searchToDo() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchToDo(query)
                    binding.scrollView.remove()
                }
                if (query!!.isBlank()) {
                    binding.scrollView.show()
                }
                return true
            }

        })

    }

    private fun filterBtn() = with(binding) {
        allToDoBtn.setOnClickListener {
            viewModel.getToDoList()
        }
        redBtnFilter.setOnClickListener {
            viewModel.getTodoByPriority(1)
        }
        purpleBtnFilter.setOnClickListener {
            viewModel.getTodoByPriority(2)
        }
        orangeBtnFilter.setOnClickListener {
            viewModel.getTodoByPriority(3)
        }
        blueBtnFilter.setOnClickListener {
            viewModel.getTodoByPriority(4)
        }
    }


    private fun searchToDo(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchToDo(searchQuery).observe(viewLifecycleOwner) {
            adapter.toDoList = it
        }
    }

    private fun recyclerEmpty() {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty() {
                binding.emptyToDo.visibility =
                    (if (adapter.itemCount == 0) View.VISIBLE else View.GONE)
            }
        })
    }

}